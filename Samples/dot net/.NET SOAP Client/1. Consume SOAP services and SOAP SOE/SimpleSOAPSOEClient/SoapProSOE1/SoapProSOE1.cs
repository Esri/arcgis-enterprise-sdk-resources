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

using System.Runtime.InteropServices;

using ESRI.ArcGIS.esriSystem;
using ESRI.ArcGIS.Server;
using ESRI.ArcGIS.Geometry;
using ESRI.ArcGIS.Geodatabase;
using ESRI.ArcGIS.Carto;

using ESRI.Server.SOESupport;

//This is SOAP SOE template of Enterprise SDK

//TODO: sign the project (project properties > signing tab > sign the assembly)
//      this is strongly suggested if the dll will be registered using regasm.exe <your>.dll /codebase


namespace SoapProSOE1
{
    [ComVisible(true)]
    [Guid("a623c681-34b6-4a1a-ac0f-d534544509fd")]
    [ClassInterface(ClassInterfaceType.None)]
    [ServerObjectExtension("MapServer",
        AllCapabilities = "",
        DefaultCapabilities = "",
        Description = "",
        DisplayName = "SoapProSOE1",
        Properties = "",
        SupportsREST = false,
        SupportsSOAP = true,
        SOAPNamespaceURI = "http://www.myOrg.com/schemas/1.0",
        SupportsSharedInstances = false)]
    public class SoapProSOE1 : IRequestHandler2, IServerObjectExtension, IObjectConstruct
    {
        internal static string c_ns_soe = "http://www.myOrg.com/schemas/1.0"; //todo: define your namespace. Keep in sych with wsdl.


        internal static string c_ns_esri = "http://www.esri.com/schemas/ArcGIS/2.6.0";

        private string soe_name;

        private IServerObjectHelper serverObjectHelper;
        private ServerLogger logger;
        private IPropertySet configProps;

        IRequestHandler2 reqHandler;


        public SoapProSOE1()
        {
            soe_name = this.GetType().Name;
            logger = new ServerLogger();
            SoeSoapImpl soapImpl = new SoeSoapImpl(soe_name, HandleSoapMessage);
            reqHandler = (IRequestHandler2)soapImpl;
        }


        public void HandleSoapMessage(IMessage reqMsg, IMessage respMsg)
        {
            string methodName = reqMsg.Name;

            //the xml element for SOAP requests is BufferGeometry. It is defined in the wsdl
            if (string.Compare(methodName, "BufferGeometry", true) == 0)
                BufferGeometry(reqMsg, respMsg);

            else
                throw new ArgumentException("Method not supported: " + QualifiedMethodName(soe_name, methodName));
        }

        private string QualifiedMethodName(string soeName, string methodName)
        {
            return soeName + "." + methodName;
        }


        #region IRequestHandler2 Members

        public byte[] HandleBinaryRequest(ref byte[] request)
        {
            throw new NotImplementedException();
        }

        public byte[] HandleBinaryRequest2(string Capabilities, ref byte[] request)
        {
            throw new NotImplementedException();
        }

        public string HandleStringRequest(string Capabilities, string request)
        {
            return reqHandler.HandleStringRequest(Capabilities, request);
        }

        #endregion

        #region IServerObjectExtension Members

        public void Init(IServerObjectHelper pSOH)
        {
            serverObjectHelper = pSOH;
        }

        public void Shutdown()
        {
            //todo: release any resources
        }

        #endregion

        #region IObjectConstruct Members

        public void Construct(IPropertySet props)
        {
            configProps = props;
        }

        #endregion


        #region wrapperFunctions

        private void BufferGeometry(IMessage reqMsg, IMessage respMsg)
        {
            IXMLSerializeData reqParams = reqMsg.Parameters;

            //get Geometry
            int idx = reqParams.Find("Geometry");   //name of the xml element in the wsdl for this parameter
            if (idx == -1)
                throw new ArgumentNullException("Geometry");

            IGeometry geom = (IGeometry)reqParams.GetObject(idx, c_ns_esri, "Geometry");

            //get Distance
            idx = reqParams.Find("Distance");       //name of the xml element in the wsdl for this parameter
            if (idx == -1)
                throw new ArgumentNullException("Distance");

            double distance = reqParams.GetDouble(idx);

            //execute
            IPolygon buffer = BufferGeometry(geom, distance);

            //fill response
            respMsg.Name = "BufferGeometryResponse";        //this is the name of the xml element for the SOAP response. It must match the wsdl.
            respMsg.NamespaceURI = c_ns_soe;                //the response message uses your namespace (not ESRI's)
            respMsg.Parameters.AddObject("Result", buffer); //the name of the xml element for the output parameter is Result. This must match the wsdl.
        }

        #endregion

        #region businessMethods

        private IPolygon BufferGeometry(IGeometry geom, double distance)
        {
            return ((ITopologicalOperator)geom).Buffer(distance) as IPolygon;
        }

        #endregion
    }
}
