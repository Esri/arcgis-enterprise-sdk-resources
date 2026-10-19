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

using ESRI.ArcGIS.Carto;
using ESRI.ArcGIS.DatasourcesGDB;
using ESRI.ArcGIS.esriSystem;
using ESRI.ArcGIS.Geodatabase;
using ESRI.ArcGIS.Geometry;
using ESRI.ArcGIS.Server;
using ESRI.Server.SOESupport;
using Microsoft.VisualBasic;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Data;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Text.Json;

//This is REST SOE template of Enterprise SDK

//TODO: sign the project (project properties > signing tab > sign the assembly)
//      this is strongly suggested if the dll will be registered using regasm.exe <your>.dll /codebase


namespace TelecomDomainNetworkRESTSOE
{
    [ComVisible(true)]
    [Guid("7fe96c07-befc-4ebe-8962-43e9459cd93f")]
    [ClassInterface(ClassInterfaceType.None)]
    [ServerObjectExtension("MapServer",
        AllCapabilities = "",
        DefaultCapabilities = "",
        Description = "Telecom Domain Network REST SOE sample reading a mobile geodatabase to perform telecom domain network operations",
        DisplayName = "Telecom Domain Network REST SOE",
        Properties = "",
        SupportsREST = true,
        SupportsSOAP = false,
        SupportsSharedInstances = false)]
    public class TelecomDomainNetworkRESTSOE : IServerObjectExtension, IObjectConstruct, IRESTRequestHandler
    {
        private string soe_name;

        private IPropertySet configProps;
        private IServerObjectHelper serverObjectHelper;
        private static readonly ServerLogger logger = new ServerLogger();
        private IRESTRequestHandler reqHandler;

        #region Telecom domain network sample private members
        static int unVersion = -1;
        static string gdbPath = @"C:\TelecomUtilityNetwork\TelecomUtilityNetwork.geodatabase";
        // Find the telecom domain network
        static ITelecomDomainNetwork telecomDomainNetwork = null;
        static IDataset unDataset = null;
        #endregion

        public TelecomDomainNetworkRESTSOE()
        {
            soe_name = this.GetType().Name;
            reqHandler = new SoeRestImpl(soe_name, CreateRestSchema()) as IRESTRequestHandler;
        }

        #region Logging methods
        private const string LogSource = "TelecomDomainNetworkRESTSOE";

        private static void LogInfo(string message)
        {
            logger.LogMessage(ServerLogger.msgType.debug, LogSource, 12000, message);
        }

        private static void LogError(string message)
        {
            logger.LogMessage(ServerLogger.msgType.debug, LogSource, 15000, message);
        }
        
        #endregion

        #region IServerObjectExtension Members

        public void Init(IServerObjectHelper pSOH)
        {
            serverObjectHelper = pSOH;
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

            RestOperation sampleOper = new RestOperation("TelecomDomainNetworkOperation",
                                                      new string[] { "MobileGeodatabasePath" },
                                                      new string[] { "json" },
                                                      SampleOperHandler);

            rootRes.operations.Add(sampleOper);

            return rootRes;
        }

        private byte[] RootResHandler(NameValueCollection boundVariables, string outputFormat, string requestProperties, out string responseProperties)
        {
            responseProperties = null;

            var json = JsonSerializer.Serialize(
                new
                {
                    UtilityNetworkDomain = "Telecom Domain Network"
                });

            return Encoding.UTF8.GetBytes(json);
        }

        private byte[] SampleOperHandler(NameValueCollection boundVariables,
                                                  JsonObject operationInput,
                                                      string outputFormat,
                                                      string requestProperties,
                                                  out string responseProperties)
        {
            responseProperties = null;

            string geodatabasePath;
            bool found = operationInput.TryGetString("MobileGeodatabasePath", out geodatabasePath);
            if (!found || string.IsNullOrEmpty(geodatabasePath))
            {
                geodatabasePath = gdbPath; // Use default path if not provided
            }          

            var result = new
            {
                MobileGeodatabasePath = geodatabasePath,
            };
            var json = JsonSerializer.Serialize(result);

            System.Diagnostics.Debugger.Launch();

            OpenTelecomDataset(geodatabasePath);

            // Must be UN version 8.0 or higher
            if (unVersion >= 8 && telecomDomainNetwork != null)
            {
                // Get telecom domain network properties
                GetTelecomDomainNetworkProperties(telecomDomainNetwork);
                
                // Perform circuit management operations
                CircuitManagementOperations(unDataset, telecomDomainNetwork);
                
                // Perform telecom equipment management operations
                TelecomEquipmentOperations(unDataset, telecomDomainNetwork);
                
                // Get color scheme information
                GetColorSchemeInfo(unDataset, telecomDomainNetwork);
            }
            else
            {
                LogInfo("Telecom domain network not found or UN version is less than 8.0.");
            }

            return Encoding.UTF8.GetBytes(json);
        }


        #region Open Telecom Dataset
        private static void OpenTelecomDataset(string gdbPath)
        {
            IWorkspaceFactory2 workspaceFactory = new SqliteWorkspaceFactoryClass();
            IWorkspace workspace = workspaceFactory.OpenFromFile(gdbPath, 0);
            var workspaceType = workspace.WorkspaceFactory.WorkspaceType;
            IEnumDataset datasets = workspace.get_Datasets(esriDatasetType.esriDTFeatureDataset);
            IDataset dataset = datasets.Next();
            IFeatureDataset featureDataset = dataset as IFeatureDataset;
            IFeatureDatasetExtensionContainer featureDatasetExtensionContainer = featureDataset as IFeatureDatasetExtensionContainer;
            IFeatureDatasetExtension featureDatasetExtension = featureDatasetExtensionContainer.FindExtension(esriDatasetType.esriDTUtilityNetwork);
            IDatasetContainer3 datasetContainer3 = featureDatasetExtension as IDatasetContainer3;
            IEnumDatasetName enumDatasetNames = datasetContainer3.DatasetNames[esriDatasetType.esriDTUtilityNetwork];

            IDatasetName datasetName = enumDatasetNames.Next();
            if (datasetName != null)
            {
                unDataset = datasetContainer3.DatasetByName[esriDatasetType.esriDTUtilityNetwork, datasetName.Name];
                LogInfo($"Dataset Name: {datasetName.Name}, Type: {datasetName.Type}");
            }

            IDatasetComponent dsComponent = (IDatasetComponent)unDataset;
            IDEBaseNetwork deBaseNetwork = (IDEBaseNetwork)dsComponent.DataElement;

            unVersion = deBaseNetwork.SchemaGeneration;

            IDEUtilityNetwork deUtilityNetwork = (IDEUtilityNetwork)dsComponent.DataElement;
            long domainNetworkCount = deUtilityNetwork.DomainNetworks.Count;
            for (int i = 0; i < domainNetworkCount; i++)
            {
                IDomainNetwork domainNetwork = (IDomainNetwork)deUtilityNetwork.DomainNetworks.Element[i];
                if (domainNetwork.DomainNetworkName == "Telco")
                {
                    telecomDomainNetwork = domainNetwork as ITelecomDomainNetwork;
                    break;
                }
            }
        }
        #endregion

        #region Circuit Management
        private static void CircuitManagementOperations(IDataset unDataset, ITelecomDomainNetwork telecomDomainNetwork)
        {
            IUtilityNetworkCircuit utilityNetworkCircuit = unDataset as IUtilityNetworkCircuit;
            IUNCircuitManager circuitManager = utilityNetworkCircuit.CreateCircuitManager(telecomDomainNetwork.DomainNetworkName);

            IWorkspaceEdit workspaceEdit = unDataset.Workspace as IWorkspaceEdit;
            bool saveEdits = true;

            #region Create circuits

            workspaceEdit.StartEditing(false);
            workspaceEdit.StartEditOperation();
            try
            {
                // Creating a sectioned circuit
                ICircuitLocation startCircuitLocationSection1 = CreateCircuitLocation(telecomDomainNetwork, "TelcoJunctionObject",
                    new Guid("10EAD56F-0CFF-47B2-8167-EE23EBBF0106"), 1, 1);
                ICircuitLocation stopCircuitLocationSection1 = CreateCircuitLocation(telecomDomainNetwork, "TelcoJunctionObject",
                    new Guid("375D40F0-821B-4C94-B329-C53AD0F777C8"), 1, 1);

                ICircuitLocation startCircuitLocationSection2 = CreateCircuitLocation(telecomDomainNetwork, "TelcoJunctionObject",
                   new Guid("375D40F0-821B-4C94-B329-C53AD0F777C8"), 1, 1);
                ICircuitLocation stopCircuitLocationSection2 = CreateCircuitLocation(telecomDomainNetwork, "TelcoJunctionObject",
                    new Guid("FA72AB4E-EBCD-427C-8AA6-C0061E854BC8"), 1, 1);

                ICircuitSection circuitSection1 = circuitManager.CreateCircuitSectionObject();
                circuitSection1.StartLocation = startCircuitLocationSection1;
                circuitSection1.StopLocation = stopCircuitLocationSection1;
                circuitSection1.SectionType = esriCircuitSectionType.esriCSTPhysical;
                circuitSection1.SectionID = 1;

                ICircuitSection circuitSection2 = circuitManager.CreateCircuitSectionObject();
                circuitSection2.StartLocation = stopCircuitLocationSection1;
                circuitSection2.StopLocation = stopCircuitLocationSection2;
                circuitSection2.SectionType = esriCircuitSectionType.esriCSTPhysical;
                circuitSection2.SectionID = 2;

                IArray circuitSections = new ArrayClass();
                circuitSections.Add(circuitSection1);
                circuitSections.Add(circuitSection2);

                ICircuit circuit = circuitManager.CreateCircuitObject();
                circuit.Name = "MySectionedCircuit";
                circuit.CircuitType = esriCircuitType.esriCTPhysical;
                circuit.IsSectioned = true;

                circuit.CircuitSections = circuitSections;

                // Set user defined attribute field's value (DESCRIPTION) for the circuit
                IRowBuffer2 circuitRowBuffer = circuit as IRowBuffer2;
                IFields fields = circuitRowBuffer.Fields;
                int fieldIndex = fields.FindField("DESCRIPTION");
                circuitRowBuffer.set_Value(fieldIndex, "This is a sectioned circuit");

                // Set the section order for the circuit
                string sectionOrderJSONString = "{\"1\":[2],\"2\":[]}";
                IJSONObject sectionOrderJSONObj = new JSONObjectClass();
                sectionOrderJSONObj.ParseString(sectionOrderJSONString);
                circuit.SectionOrder = sectionOrderJSONObj;

                circuitManager.CreateCircuit(circuit);

                /*
                // Creating a unsectioned circuit
                ICircuitLocation startCircuitLocation = CreateCircuitLocation(telecomDomainNetwork, "TelcoJunctionObject",
                    new Guid("A0D3447F-AF80-4FDF-A61E-3329ED2C1623"), 1, 1);
                ICircuitLocation stopCircuitLocation = CreateCircuitLocation(telecomDomainNetwork, "TelcoJunctionObject",
                    new Guid("8A6CE597-EE12-4957-B05F-551692A41596"), 1, 1);

                ICircuit unSectionedCircuit = circuitManager.CreateCircuitObject();
                unSectionedCircuit.StartLocation = startCircuitLocation;
                unSectionedCircuit.StopLocation = stopCircuitLocation;
                unSectionedCircuit.Name = "MyUnSectionedCircuit";
                unSectionedCircuit.CircuitType = esriCircuitType.esriCTPhysical;
                unSectionedCircuit.IsSectioned = false;

                circuitManager.CreateCircuit(unSectionedCircuit);
                */
            }
            catch (Exception ex)
            {
                LogError($"Error creating circuit: {ex.Message}");
                saveEdits = false;
            }
            finally
            {
                workspaceEdit.StopEditOperation();
                workspaceEdit.StopEditing(saveEdits);
            }

            #endregion Create circuits

            #region Query circuits
            IArray circuitArray = new ArrayClass();

            IStringArray circuitNames = new StrArrayClass();
            circuitNames.Add("MySectionedCircuit");

            circuitManager.GetCircuits(circuitNames, null, null, esriCircuitQueryLocationType.esriCQLTNotApplicable,
                esriCircuitHierarchy.esriCHConsumersAndProviders, esriCircuitStatusFilter.esriCSFAll, out circuitArray);

            for (int i = 0; i < circuitArray.Count; i++)
            {
                ICircuit circuit = circuitArray.Element[i] as ICircuit;
                if (circuit != null)
                {
                    LogInfo($"Circuit Name: {circuit.Name}, Circuit Type: {circuit.CircuitType}, Is Sectioned: {circuit.IsSectioned}");
                }
            }
            #endregion Query circuits

            #region Verify circuits
            workspaceEdit.StartEditing(false);
            workspaceEdit.StartEditOperation();
            try
            {
                IArray circuitResultsInfo = new ArrayClass();
                circuitManager.VerifyCircuits(circuitNames, true, true, false, null, out circuitResultsInfo);
                for (int i = 0; i < circuitResultsInfo.Count; i++)
                {
                    ICircuitResultInfo circuitResultInfo = circuitResultsInfo.Element[i] as ICircuitResultInfo;
                    if (circuitResultInfo != null)
                    {
                        LogInfo($"Circuit Name: {circuitResultInfo.Name}, Status: {circuitResultInfo.Status} ,  Message: {circuitResultInfo.Message}, Error Code: {circuitResultInfo.ErrorCode}");
                    }
                }
            }
            catch (Exception ex)
            {
                LogError($"Error verifying circuit: {ex.Message}");
                saveEdits = false;
            }
            finally
            {
                workspaceEdit.StopEditOperation();
                workspaceEdit.StopEditing(saveEdits);
            }
            #endregion Verify circuits

        }
        #endregion Circuit Management


        #region Equipment Management
        private static void TelecomEquipmentOperations(IDataset unDataset, ITelecomDomainNetwork telecomDomainNetwork)
        {
            IUtilityNetworkEquipmentIdentifiers utilityNetworkEquipmentIdentifiers = (IUtilityNetworkEquipmentIdentifiers)unDataset;

            // Query equipment identifiers/contents in a container device
            ILongArray gapStartUnitIds, gapEndUnitIds, unitIdentifierContentNetworkSourceId, firstUnits, lastUnits;
            IStringArray unitIdentifierContentGlobalIds;
            INetworkSource containerNetworkSource = GetNetworkSourceFromDatasetName(telecomDomainNetwork, "TelcoDevice");
            Guid portContainerGlobalId = new Guid("F0CCEEAB-52BD-431F-8B8C-E22820F4BB61");
            utilityNetworkEquipmentIdentifiers.QueryUnitIdentifiable(containerNetworkSource.ID, portContainerGlobalId, out gapStartUnitIds, out gapEndUnitIds, out unitIdentifierContentNetworkSourceId, out unitIdentifierContentGlobalIds, out firstUnits, out lastUnits);

            LogInfo($" Container Source ID:{containerNetworkSource.ID},Container GUID:{portContainerGlobalId} Content Source ID: {unitIdentifierContentNetworkSourceId.Element[0].ToString()} Content GUID:{unitIdentifierContentGlobalIds.Element[0].ToString()}");

            IWorkspaceEdit workspaceEdit = unDataset.Workspace as IWorkspaceEdit;
            bool saveEdits = true;

            workspaceEdit.StartEditing(false);
            workspaceEdit.StartEditOperation();
            try
            {
                // Reserve unit spaces in a container device
                utilityNetworkEquipmentIdentifiers.ReserveUnitID(containerNetworkSource.ID, portContainerGlobalId, 11, 20);

                // Resize the number of units owned by a unit identifier in a container device
                utilityNetworkEquipmentIdentifiers.ResizeUnitID(unitIdentifierContentNetworkSourceId.Element[0], new Guid(unitIdentifierContentGlobalIds.Element[0].ToString()), 21);

                // Reset
                utilityNetworkEquipmentIdentifiers.ResetUnitID(containerNetworkSource.ID, portContainerGlobalId);
            }
            catch (Exception ex)
            {
                LogError($"Error handling equipment: {ex.Message}");
                saveEdits = false;
            }
            finally
            {
                workspaceEdit.StopEditOperation();
                workspaceEdit.StopEditing(saveEdits);
            }
        }
        #endregion Equipment Management

        #region Telecom Domain Network Properties
        private static void GetTelecomDomainNetworkProperties(ITelecomDomainNetwork telecomDomainNetwork)
        {
            LogInfo($"Telecom Domain Network Name: {telecomDomainNetwork.DomainNetworkName}");
            IArray colorSets = telecomDomainNetwork.ColorSets;
            IArray wavelengthSchemes = telecomDomainNetwork.WavelengthSchemes;
            IPropertySet circuitProperties = telecomDomainNetwork.CircuitProperties;

            IArray circuitUserAttributes = telecomDomainNetwork.CircuitUserAttributes;
            for (int i = 0; i < circuitUserAttributes.Count; i++)
            {
                ICircuitUserAttribute circuitUserAttribute = circuitUserAttributes.get_Element(i) as ICircuitUserAttribute;
            }
        }
        #endregion

        #region Telecom Color Code Information
        private static void GetColorSchemeInfo(IDataset unDataset, ITelecomDomainNetwork telecomDomainNetwork)
        {
            // Get various color code information of a telecom domain network
            IUtilityNetworkColorScheme utilityNetworkColorScheme = (IUtilityNetworkColorScheme)unDataset;

            string groupName = utilityNetworkColorScheme.GetGroupName(telecomDomainNetwork.DomainNetworkName, 5);
            string labels = utilityNetworkColorScheme.GetLabelsByLevel(telecomDomainNetwork.DomainNetworkName, 1, 2);
            IArray colorCodes = utilityNetworkColorScheme.GetColorCodesByLevel(telecomDomainNetwork.DomainNetworkName, 2, 2);

            ILongArray colorCodeIDs = new LongArrayClass();
            colorCodeIDs.Add(1);

            IStringArray labels1 = utilityNetworkColorScheme.GetLabels(telecomDomainNetwork.DomainNetworkName, 1, colorCodeIDs);
            IArray colorCodes1 = utilityNetworkColorScheme.GetColorCodes(telecomDomainNetwork.DomainNetworkName, 1, colorCodeIDs);
        }

        #endregion Telecom Color Code Information

        #region Create a Circuit Location
        private static ICircuitLocation CreateCircuitLocation(ITelecomDomainNetwork telecomDomainNetwork, string datasetName, Guid globalId, short firstUnit, short lastUnit)
        {
            INetworkSource networkSource = GetNetworkSourceFromDatasetName(telecomDomainNetwork, datasetName);
            ICircuitLocation circuitLocation = new CircuitLocationClass
            {
                FirstUnit = firstUnit,
                LastUnit = lastUnit,
                SourceID = (short)networkSource.ID,
                GlobalID = globalId
            };

            return circuitLocation;
        }
        #endregion

        private static INetworkSource GetNetworkSourceFromDatasetName(ITelecomDomainNetwork telecomDomainNetwork, string datasetName)
        {
            IArray networkSources = telecomDomainNetwork.Sources;
            long networkSourceCount = networkSources.Count;
            for (int i = 0; i < networkSourceCount; i++)
            {
                INetworkSource networkSource = networkSources.Element[i] as INetworkSource;
                if (networkSource.Name.Contains(datasetName))
                {
                    LogInfo($"Network Source Name: {networkSource.Name}, Type: {networkSource.SourceType}, ID: {networkSource.ID}");

                    return networkSource;
                }
            }
            return null;
        }

    }
}
