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
using ESRI.ArcGIS.esriSystem;
using ESRI.ArcGIS.Geodatabase;
using ESRI.ArcGIS.Geometry;
using ESRI.ArcGIS.Server;
using ESRI.Server.SOESupport;
using ESRI.Server.SOESupport.SOI;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Text.Json;

//This is REST SOE template of Enterprise SDK

//TODO: sign the project (project properties > signing tab > sign the assembly)
//      this is strongly suggested if the dll will be registered using regasm.exe <your>.dll /codebase


namespace NetUNEditAreasSOI
{
  [ComVisible(true)]
  [Guid("0fd7cdb2-bc20-48ea-9a56-84c2b87b5218")]
  [ClassInterface(ClassInterfaceType.None)]
  [ServerObjectInterceptor("MapServer",
      Description = "",
      DisplayName = "NetUNEditAreasSOI",
      Properties = "",
      SupportsSharedInstances = false)]
  public class NetUNEditAreasSOI : IServerObjectExtension, IRESTRequestHandler, IWebRequestHandler, IRequestHandler2, IRequestHandler
  {
    private string _soiName;
    private IServerObjectHelper _soHelper;
    private ServerLogger _serverLog;
    private RestSOIHelper _restSOIHelper;
    private SOIUtil _soiUtil; // Helper class to work with the Utility Network

    // Edit Areas Feature Class Constants
    private const string _editAreasFCName = "EditAreas";
    private const string _creatorFName = "CREATOR";
    private const string _creationDateFName = "CREATIONDATE";
    private const string _lastUpdateFName = "LASTUPDATE";
    private const string _editCountFName = "EDITCOUNT";
    private const string _versionNameFName = "VERSIONNAME";
    private const string _errorCodeFName = "ERRORCODE";

    public NetUNEditAreasSOI()
    {
      _soiName = this.GetType().Name;
    }

    public void Init(IServerObjectHelper pSOH)
    {
      // Uncomment this line to turn on the debugger
      //System.Diagnostics.Debugger.Launch();

      _soHelper = pSOH;
      _serverLog = new ServerLogger();
      _restSOIHelper = new RestSOIHelper(pSOH);
      _soiUtil = new SOIUtil();

      _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".init()", 200, "Initialized " + _soiName + " SOI.");
    }

    public void Shutdown()
    {
      _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".init()", 200, "Shutting down " + _soiName + " SOI.");
    }

    #region REST interceptors

    public string GetSchema()
    {
      IRESTRequestHandler restRequestHandler = _restSOIHelper.FindRequestHandlerDelegate<IRESTRequestHandler>();
      if (restRequestHandler == null)
        return null;

      return restRequestHandler.GetSchema();
    }

    public byte[] HandleRESTRequest(string Capabilities, string resourceName, string operationName,
        string operationInput, string outputFormat, string requestProperties, out string responseProperties)
    {
      byte[] response = null;
      responseProperties = null;
      _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".HandleRESTRequest()",
          200, "Request received in Sample Object Interceptor for handleRESTRequest");

      // Pre-processing
      // Only intercept calls to UtilityNetworkServer/validateNetworkTopology operation
      if (_soiUtil.GetServerExtensionName() == "UtilityNetworkServer" && operationName == "validateNetworkTopology")
      {
        // Parse input parameters
        string versionName = null;
        JSONObject inputJSON = _soiUtil.ParseParameters(operationInput);
        inputJSON.TryGetValueAsString("gdbVersion", out versionName);

        if (!String.IsNullOrEmpty(versionName) && !versionName.Equals("sde.DEFAULT", StringComparison.OrdinalIgnoreCase))
          response = ProcessEditAreas(_soHelper.ServerObject, versionName);

        if (response != null)
          return response;
      }

      // Find the correct delegate to forward the request too
      IRESTRequestHandler restRequestHandler = _restSOIHelper.FindRequestHandlerDelegate<IRESTRequestHandler>();
      if (restRequestHandler == null)
        return null;

      response = restRequestHandler.HandleRESTRequest(
          Capabilities, resourceName, operationName, operationInput,
          outputFormat, requestProperties, out responseProperties);

      // Post-processing
      // Only intercept calls to VersionManagementServer/delete operation
      if (_soiUtil.GetServerExtensionName() == "VersionManagementServer" && operationName == "delete")
      {
        bool deleteSuccess = false;

        // Parse response
        JSONObject responseJSON = _soiUtil.ParseResponse(response);
        responseJSON.TryGetValueAsBoolean("success", out deleteSuccess);

        if (deleteSuccess)
        {
          // Parse input parameters
          string versionName = null;
          JSONObject inputJSON = _soiUtil.ParseParameters(operationInput);
          inputJSON.TryGetValueAsString("versionName", out versionName);

          RemoveEditAreas(_soHelper.ServerObject, versionName);
        }
      }

      return response;
    }

    #endregion

    #region SOAP interceptors

    public byte[] HandleStringWebRequest(esriHttpMethod httpMethod, string requestURL,
        string queryString, string Capabilities, string requestData,
        out string responseContentType, out esriWebResponseDataType respDataType)
    {
      _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".HandleStringWebRequest()",
          200, "Request received in Sample Object Interceptor for HandleStringWebRequest");

      /*
       * Add code to manipulate requests here
       */

      IWebRequestHandler webRequestHandler = _restSOIHelper.FindRequestHandlerDelegate<IWebRequestHandler>();
      if (webRequestHandler != null)
      {
        return webRequestHandler.HandleStringWebRequest(
                httpMethod, requestURL, queryString, Capabilities, requestData, out responseContentType, out respDataType);
      }

      responseContentType = null;
      respDataType = esriWebResponseDataType.esriWRDTPayload;
      //Insert error response here.
      return null;
    }

    public byte[] HandleBinaryRequest(ref byte[] request)
    {
      _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".HandleBinaryRequest()",
            200, "Request received in Sample Object Interceptor for HandleBinaryRequest");

      /*
       * Add code to manipulate requests here
       */

      IRequestHandler requestHandler = _restSOIHelper.FindRequestHandlerDelegate<IRequestHandler>();
      if (requestHandler != null)
      {
        return requestHandler.HandleBinaryRequest(request);
      }

      //Insert error response here.
      return null;
    }

    public byte[] HandleBinaryRequest2(string Capabilities, ref byte[] request)
    {
      _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".HandleBinaryRequest2()",
            200, "Request received in Sample Object Interceptor for HandleBinaryRequest2");

      /*
       * Add code to manipulate requests here
       */

      IRequestHandler2 requestHandler = _restSOIHelper.FindRequestHandlerDelegate<IRequestHandler2>();
      if (requestHandler != null)
      {
        return requestHandler.HandleBinaryRequest2(Capabilities, request);
      }

      //Insert error response here.
      return null;
    }

    public string HandleStringRequest(string Capabilities, string request)
    {
      _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".HandleStringRequest()",
             200, "Request received in Sample Object Interceptor for HandleStringRequest");

      /*
       * Add code to manipulate requests here
       */

      IRequestHandler requestHandler = _restSOIHelper.FindRequestHandlerDelegate<IRequestHandler>();
      if (requestHandler != null)
      {
        return requestHandler.HandleStringRequest(Capabilities, request);
      }

      //Insert error response here.
      return null;
    }

    #endregion

    #region SOI Helper Methods
    private byte[] ProcessEditAreas(IServerObject serverObject, string versionName)
    {
      try
      {
        // Open utility network
        IDataset unDataset = _soiUtil.GetUNDataset(serverObject, versionName);
        IWorkspace workspace = (IWorkspace)unDataset.Workspace;

        // Get all the dirty areas in the given validation area
        IBaseNetwork baseNetwork = (IBaseNetwork)unDataset;
        ITable dirtyAreaTable = baseNetwork.DirtyAreaTable;

        string shapeFieldName = ((IFeatureClass)dirtyAreaTable).ShapeFieldName;
        int areaFieldIndex = dirtyAreaTable.FindField(shapeFieldName);
        int creatorFieldIndex = dirtyAreaTable.FindField("CREATOR");

        // Get UN schema version
        IDatasetComponent dsComponent = (IDatasetComponent)unDataset;
        IDEBaseNetwork deBaseNetwork = (IDEBaseNetwork)dsComponent.DataElement;
        int unVersion = deBaseNetwork.SchemaGeneration;

        // Get inserts made to dirty areas table in the current version
        // For UN > V4, Errors are discarded (ERROCODE>0) to only retain true dirty areas
        // Note that changes made in the last edit session must be saved for this to work
        // as it is not possible to get the modifications until they have been saved.

        IVersionedTable versionedTable = (IVersionedTable)dirtyAreaTable;
        QueryFilter qryFilter = null;
        if (unVersion >= 4)
        {
          qryFilter = new QueryFilter();
          qryFilter.WhereClause = _errorCodeFName + "=0";
        }

        IDifferenceCursor diffCursor = versionedTable.Differences(dirtyAreaTable, esriDifferenceType.esriDifferenceTypeInsert, qryFilter);

        // Loop through added rows to construct the modified zone extent
        int editCount = 0;
        long OID;
        IRow diffRow;
        IEnvelope editZone = null;
        string creator = "";

        diffCursor.Next(out OID, out diffRow);

        // Return an error if no dirty areas found as it may be because the last edits were not saved
        if (diffRow == null)
        {
          JSONObject responseJSON = new JSONObject();
          responseJSON.AddBoolean("success", false);
          JSONObject errorJSON = new JSONObject();
          errorJSON.AddLong("extendedCode", (int)fdoError.FDO_E_DIRTY_AREA_BUILD_EXTENT_DO_NOT_INTERSECT);
          errorJSON.AddString("message", "A dirty area is not present within the validate network topology input extent. A validate network topology process did not occur.");
          JSONArray detailsJSON = new JSONArray();
          detailsJSON.AddString("Make sure to save edits before validating.");
          errorJSON.AddJSONArray("details", detailsJSON);
          responseJSON.AddJSONObject("error", errorJSON);

          return Encoding.UTF8.GetBytes(responseJSON.ToJSONString(null));
        }

        while (diffRow != null)
        {

          editCount += 1;
          creator = diffRow.Value[creatorFieldIndex].ToString();
          IGeometry rowShape = (IGeometry)diffRow.Value[areaFieldIndex];
          IEnvelope rowArea = rowShape.Envelope;
          if (editZone != null)
            editZone.Union(rowArea);
          else
            editZone = rowArea.Envelope;

          diffCursor.Next(out OID, out diffRow);
        }
        diffCursor = null;
        versionedTable = null;
        workspace = null;

        // Add new or modify existing edit zone
        if (editZone != null)
          AddEditArea(serverObject, creator, versionName, editCount, editZone);
      }
      catch (Exception e)
      {
        _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".AddEditArea()",
            200, "Error while adding edit are: " + e.ToString());
      }

      return null;
    }

    private bool AddEditArea(IServerObject serverObject, string creator, string versionName, int editCount, IEnvelope editZone, bool autoExpandZone = true)
    {
      bool isOk = false;
      IFeatureClass editAreasFC = null;

      try
      {
        // Open edit areas feature class in default workspace
        editAreasFC = GetEditAreaFeatureClass(serverObject);

        // Get field indices
        int creatorFIdx = editAreasFC.FindField(_creatorFName);
        int creationDateFIdx = editAreasFC.FindField(_creationDateFName);
        int lastUpdateFIdx = editAreasFC.FindField(_lastUpdateFName);
        int editCountFIdx = editAreasFC.FindField(_editCountFName);
        int versionNameFIdx = editAreasFC.FindField(_versionNameFName);

        DateTime currentTime = DateTime.Now;

        // Expand zone to make it more visible
        if (autoExpandZone)
        {
          double expandRatio = (editCount <= 3) ? 1.5 : 1.15;
          editZone.Expand(expandRatio, expandRatio, true);
        }

        // Check if there's an existing area
        IFeature curFeature = null;
        IFeatureCursor fCursor = GetEditAreas(serverObject, versionName);

        if (fCursor != null && (curFeature = fCursor.NextFeature()) != null)
        {
          // Union the edit zones
          IPolygon curZone = (IPolygon)curFeature.Shape;
          IGeometry geometryBag = new GeometryBag();
          geometryBag.SpatialReference = curZone.SpatialReference;
          IGeometryCollection geometryCollection = (IGeometryCollection)geometryBag;
          geometryCollection.AddGeometry(curZone);
          geometryCollection.AddGeometry(editZone);

          Polygon zoneConstructor = new Polygon();
          ITopologicalOperator newZone = (ITopologicalOperator)zoneConstructor;
          newZone.ConstructUnion((IEnumGeometry)geometryCollection);

          // Update feature values
          curFeature.Shape = (IGeometry)newZone;
          curFeature.Value[lastUpdateFIdx] = currentTime;
          int curCount = (int)curFeature.Value[editCountFIdx];
          curFeature.Value[editCountFIdx] = curCount + editCount;

          // Store feature
          curFeature.Store();
          curFeature = null;
        }
        else
        {
          // Save edit zone to feature class
          IFeature zoneFeature = editAreasFC.CreateFeature();
          zoneFeature.Value[creatorFIdx] = creator;
          zoneFeature.Value[creationDateFIdx] = currentTime;
          zoneFeature.Value[lastUpdateFIdx] = currentTime;
          zoneFeature.Value[versionNameFIdx] = versionName;
          zoneFeature.Value[editCountFIdx] = editCount;

          // Set geometry
          Polygon editAreaPoly = new Polygon();
          ISegmentCollection editAreaSeg = (ISegmentCollection)editAreaPoly;
          editAreaSeg.SetRectangle(editZone);
          IZAware polyZAware = editAreaPoly as IZAware;
          polyZAware.ZAware = false;
          zoneFeature.Shape = polyZAware as IGeometry;

          // Store feature
          zoneFeature.Store();
          fCursor.Flush();
          fCursor = null;
          zoneFeature = null;
        }

        isOk = true;
      }
      catch (Exception e)
      {
        _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".AddEditArea()",
            200, "Error while adding edit are: " + e.ToString());
      }
      finally
      {
        editAreasFC = null;
      }
      return isOk;

    }

    private bool RemoveEditAreas(IServerObject serverObject, string versionName)
    {
      bool isOk = false;
      IFeature curFeature = null;
      IFeatureCursor fCursor = null;
      try
      {
        // Search for entries for the given version
        fCursor = GetEditAreas(serverObject, versionName);

        if (fCursor != null)
          curFeature = fCursor.NextFeature();

        while (curFeature != null)
        {
          curFeature.Delete();
          curFeature = fCursor.NextFeature();
        }
        isOk = true;
        fCursor.Flush();
      }
      catch (Exception e)
      {
        _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".RemoveEditArea()",
            200, "Error while deleting edit area: " + e.ToString());
      }
      finally
      {
        fCursor = null;
        curFeature = null;
      }

      return isOk;
    }

    private IFeatureCursor GetEditAreas(IServerObject serverObject, string versionName)
    {
      IFeatureClass editAreasFC = null;
      try
      {
        // Open edit areas feature class in default workspace
        editAreasFC = GetEditAreaFeatureClass(serverObject);

        // Search for entries for the given version
        QueryFilter qryFilter = null;
        qryFilter = new QueryFilter();
        qryFilter.WhereClause = _versionNameFName + "='" + versionName + "'";
        return editAreasFC.Search(qryFilter, false);
      }
      catch (Exception e)
      {
        _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".GetEditAreas()",
            200, "Error while retrieving edit areas: " + e.ToString());
      }
      finally
      {
        editAreasFC = null;
      }
      return null;
    }

    private IFeatureClass GetEditAreaFeatureClass(IServerObject serverObject)
    {
      try
      {
        // Get GDB workspace
        IMapServer mapService = (MapServer)serverObject;
        IMapServerDataAccess mapServerDataAccess = (IMapServerDataAccess)serverObject;
        IDataset ds = (IDataset)mapServerDataAccess.GetDataSource(mapService.DefaultMapName, 0);
        IWorkspace workspace = ds.Workspace;

        // Open edit areas feature class in default workspace
        IVersionedWorkspace versionedWorkspace = (IVersionedWorkspace)workspace;
        IVersion defaultVersion = versionedWorkspace.DefaultVersion;
        IFeatureWorkspace defaultFWS = (IFeatureWorkspace)defaultVersion;
        return defaultFWS.OpenFeatureClass(_editAreasFCName);
      }
      catch (Exception e)
      {
        _serverLog.LogMessage(ServerLogger.msgType.infoStandard, _soiName + ".GetEditAreaFeatureClass()",
            200, "Error while retrieving edit areas feature class: " + e.ToString());
      }
      return null;
    }
    #endregion
  }
}
