// Copyright 2018 ESRI
// 
// All rights reserved under the copyright laws of the United States
// and applicable international laws, treaties, and conventions.
// 
// You may freely redistribute and use this sample code, with or
// without modification, provided you include the original copyright
// notice and use restrictions.
// 
// See the use restrictions at <your Enterprise SDK install location>/userestrictions.txt.
// 

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections.Specialized;
using System.Runtime.InteropServices;
using ESRI.ArcGIS.esriSystem;
using ESRI.ArcGIS.Server;
using ESRI.ArcGIS.Geometry;
using ESRI.ArcGIS.Geodatabase;
using ESRI.ArcGIS.Carto;
using ESRI.Server.SOESupport;
using System.IO;


//This is REST SOE template of Enterprise SDK

//TODO: sign the project (project properties > signing tab > sign the assembly)
//      this is strongly suggested if the dll will be registered using regasm.exe <your>.dll /codebase


namespace DownloadFileRESTSOE
{
    [ComVisible(true)]
    [Guid("e0e967c0-421f-4bdf-b565-33899e9aa5e8")]
    [ClassInterface(ClassInterfaceType.None)]
    [ServerObjectExtension("MapServer",
        AllCapabilities = "",
        DefaultCapabilities = "",
        Description = "",
        DisplayName = ".NET Download File REST SOE",
        Properties = "",
        SupportsREST = true,
        SupportsSOAP = false)]
    public class DownloadFileRESTSOE : IServerObjectExtension, IObjectConstruct, IRESTRequestHandler
    {
        private string soe_name;

        private IPropertySet configProps;
        private IServerObjectHelper serverObjectHelper;
        private ServerLogger logger;
        private IRESTRequestHandler reqHandler;
        private string localFilePath = string.Empty;
        private string virtualFilePath = string.Empty;

        public DownloadFileRESTSOE()
        {
            soe_name = this.GetType().Name;
            logger = new ServerLogger();
            reqHandler = new SoeRestImpl(soe_name, CreateRestSchema()) as IRESTRequestHandler;
        }

        #region IServerObjectExtension Members

        public void Init(IServerObjectHelper pSOH)
        {
            serverObjectHelper = pSOH;
            string _outputDirectory = null;
            try
            {
                IPropertySet configProps = ServerUtilities.QueryConfigurationProperties(pSOH.ServerObject.ConfigurationName, pSOH.ServerObject.TypeName);
                _outputDirectory = configProps.GetProperty("outputDir") as string;
            }
            catch (Exception ignore)
            {
                _outputDirectory = string.Empty;
            }

            _outputDirectory = _outputDirectory.Trim();
            if (string.IsNullOrEmpty(_outputDirectory))
            {
                logger.LogMessage(ServerLogger.msgType.error, soe_name + ".init()", 500, "OutputDirectory is empty or missing. Reset to default.");
                _outputDirectory = "C:\\arcgisserver\\directories\\arcgisoutput";
            }
            localFilePath = _outputDirectory + "\\" + pSOH.ServerObject.ConfigurationName.Replace('/', '\\') + "_" + pSOH.ServerObject.TypeName;
            virtualFilePath = pSOH.ServerObject.ConfigurationName + "_" + pSOH.ServerObject.TypeName;
        }

        public void Shutdown()
        {
        }

        #endregion

        #region IObjectConstruct Members

        public void Construct(IPropertySet props)
        {
            configProps = props;
        }

        #endregion

        #region IRESTRequestHandler Members

        public string GetSchema()
        {
            return reqHandler.GetSchema();
        }

        public byte[] HandleRESTRequest(string Capabilities, string resourceName, string operationName, string operationInput, string outputFormat, string requestProperties, out string responseProperties)
        {
            return reqHandler.HandleRESTRequest(Capabilities, resourceName, operationName, operationInput, outputFormat, requestProperties, out responseProperties);
        }

        #endregion

        private RestResource CreateRestSchema()
        {
            RestResource rootRes = new RestResource(soe_name, false, RootResHandler);

            RestResource listFilesRes = new RestResource("Files", true, ListFilesHandler);

            RestOperation downloadOp = new RestOperation("DownloadFile",
                                                      new string[] { "inputText" },
                                                      new string[] { "file", "json" },
                                                      DownloadFileHandler);

            RestOperation deleteFileOp = new RestOperation("DeleteFile",
                              new string[] { "fileName" },
                              new string[] { "json" },
                              DeleteFileHandler);

            rootRes.resources.Add(listFilesRes);
            rootRes.operations.Add(downloadOp);
            rootRes.operations.Add(deleteFileOp);

            return rootRes;
        }

        private byte[] RootResHandler(NameValueCollection boundVariables, string outputFormat, string requestProperties, out string responseProperties)
        {
            responseProperties = null;
            JsonObject result = new JsonObject();
            result.AddString("Description", "This SOE generates a text file on the Server and allows clients to download the file from the Server.\n"
                + "It also provides REST endpoints to manage those files such as obtaining file names and deleting the files.");

            return Encoding.UTF8.GetBytes(result.ToJson());
        }

        private byte[] DownloadFileHandler(NameValueCollection boundVariables,
                                                  JsonObject operationInput,
                                                      string outputFormat,
                                                      string requestProperties,
                                                  out string responseProperties)
        {
            responseProperties = "";
            ////Make sure you set the response properties (Content-Type header) properly
            string fileId = Guid.NewGuid().ToString("N");
            string fileName = "testFile_" + fileId + ".txt";
            string inputText;
            bool found = operationInput.TryGetString("inputText", out inputText);
            if (!found || string.IsNullOrEmpty(inputText))
                inputText = "default input...";
            string file = localFilePath + "\\" + fileName;
            System.IO.StreamWriter sw = System.IO.File.CreateText(file);
            sw.WriteLine(inputText);
            sw.Close();
            long fileSize = new System.IO.FileInfo(file).Length;

            if (outputFormat == "json")
            {
                responseProperties = "{\"Content-Type\" : \"application/json\"}";
                string requestURL = ServerUtilities.GetServerEnvironment().Properties.GetProperty("RequestContextURL") as string;
                string fileVirutualURL = requestURL + "/rest/directories/arcgisoutput/" + virtualFilePath + "/" + fileName;
                JsonObject jsonResult = new JsonObject();
                jsonResult.AddString("url", fileVirutualURL);
                jsonResult.AddString("fileName", fileName);
                jsonResult.AddString("fileSizeBytes", Convert.ToString(fileSize));
                return Encoding.UTF8.GetBytes(jsonResult.ToJson());

            }
            else if (outputFormat == "file")
            {
                responseProperties = "{\"Content-Type\" : \"application/octet-stream\",\"Content-Disposition\": \"attachment; filename=" + fileName + "\"}";
                return System.IO.File.ReadAllBytes(file);
            }
            return Encoding.UTF8.GetBytes("");
        }

        private byte[] ListFilesHandler(NameValueCollection boundVariables,
                                              string outputFormat,
                                              string requestProperties,
                                          out string responseProperties)
        {
            responseProperties = null;

            FileInfo[] Files = new DirectoryInfo(localFilePath).GetFiles("*.txt");
            JsonObject[] filesArr = new JsonObject[Files.Length];
            for(int i =0; i<Files.Length; i++)
            {
                JsonObject fileJson = new JsonObject();
                fileJson.AddString("filename", Files[i].Name);
                filesArr[i] = fileJson;
            }
            JsonObject filesJson = new JsonObject();
            filesJson.AddObject("files", filesArr);
            return Encoding.UTF8.GetBytes(filesJson.ToJson());
        }

        private byte[] DeleteFileHandler(NameValueCollection boundVariables,
                                          JsonObject operationInput,
                                              string outputFormat,
                                              string requestProperties,
                                          out string responseProperties)
        {
            responseProperties = "{\"Content-Type\" : \"application/json\"}";
            string fileName;
            bool found = operationInput.TryGetString("fileName", out fileName);
            if (!found || string.IsNullOrEmpty(fileName) || !File.Exists(localFilePath+ "\\" + fileName))
                return Encoding.UTF8.GetBytes("{\"error\": \"file not found.\"}");
            try
            {
                File.Delete(localFilePath + "\\" + fileName);
            }
            catch (Exception e)
            {
                return Encoding.UTF8.GetBytes("{\"success\": false, \"details\"" + e.Message + ".\"}");
            }
            
            return Encoding.UTF8.GetBytes("{\"succsss\": true}");
        }
    }
}
