// Copyright 2026 Esri
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
using ESRI.ArcGIS.CartoX;
using ESRI.ArcGIS.CIM;
using ESRI.ArcGIS.esriSystem;
using ESRI.ArcGIS.Geodatabase;
using ESRI.ArcGIS.Geometry;
using ESRI.ArcGIS.GISClient;
using ESRI.ArcGIS.Server;
using ESRI.Server.SOESupport;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Text.Json;

//TODO: sign the project (project properties > signing tab > sign the assembly)
//      this is strongly suggested if the dll will be registered using regasm.exe <your>.dll /codebase


namespace EditAnnotationRESTSOE
{
  [ComVisible(true)]
  [Guid("1736242d-846d-4d13-a57b-9ff4dde85d11")]
  [ClassInterface(ClassInterfaceType.None)]
  [ServerObjectExtension("MapServer",
      AllCapabilities = "",
      DefaultCapabilities = "",
      Description = ".Net Edit Annotation features SOE - allows feature addition and editing.",
      DisplayName = ".Net Edit Annotation Features REST SOE",
      Properties = "layerId=0",
      SupportsREST = true,
      SupportsSOAP = false,
      SupportsSharedInstances = true)]
  public class EditAnnotationRESTSOE : IServerObjectExtension, IObjectConstruct, IRESTRequestHandler
  {
    private string soe_name;

    private IPropertySet configProps;
    private IServerObjectHelper serverObjectHelper;
    private ServerLogger logger;
    private IRESTRequestHandler reqHandler;

    private IMapServerInfo mapServerInfo = null;
    private IMapServerDataAccess mapServerDataAccess = null;
    private IMapLayerInfos layerInfos = null;
    private IMapLayerInfo editLayerInfo = null;
    private int layerId = -1;
    private IFeatureClass fc = null;

    public EditAnnotationRESTSOE()
    {
      soe_name = this.GetType().Name;
      logger = new ServerLogger();
      reqHandler = new SoeRestImpl(soe_name, CreateRestSchema()) as IRESTRequestHandler;
    }

    #region IServerObjectExtension Members

    public void Init(IServerObjectHelper pSOH)
    {
      serverObjectHelper = pSOH;

      mapServerDataAccess = (IMapServerDataAccess)pSOH.ServerObject;
      IMapServer ms = (IMapServer)pSOH.ServerObject;
      this.mapServerInfo = ms.GetServerInfo(ms.DefaultMapName);
      this.layerInfos = mapServerInfo.MapLayerInfos;

      if (layerId < 0)
        layerId = 0;
    }

    public void Shutdown()
    {
    }

    #endregion

    #region IObjectConstruct Members

    public void Construct(IPropertySet props)
    {
      configProps = props;
      string lid = (string)props.GetProperty("layerId");
      this.layerId = Convert.ToInt32(lid);

      this.fc = (IFeatureClass)this.mapServerDataAccess.GetDataSource(this.mapServerInfo.Name, this.layerId);
      this.editLayerInfo = this.layerInfos.get_Element(this.layerId);
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

      RestResource layerResource = new RestResource("layers", false, LayersResHandler);
      rootRes.resources.Add(layerResource);

      RestOperation addNewFeatureOper = new RestOperation("addNewFeature",
                                                new string[] { "featureJSON" },
                                                new string[] { "json" },
                                                addNewFeatureOperHandler);
      rootRes.operations.Add(addNewFeatureOper);

      RestOperation editFeatureOper = new RestOperation("editFeature",
                                                new string[] { "featureId", "featureJSON" },
                                                new string[] { "json" },
                                                editFeatureOperHandler);
      rootRes.operations.Add(editFeatureOper);

      return rootRes;
    }

    private byte[] RootResHandler(NameValueCollection boundVariables, string outputFormat, string requestProperties, out string responseProperties)
    {
      responseProperties = null;

      JsonObject infoJSON = new JsonObject();
      infoJSON.AddString("name", ".Net Edit Annotation Features REST SOE");
      infoJSON.AddString("description", "This SOE adds and edits annotation features to a selected layer in a map service with editable data source registered to ArcGIS Server. Note that this SOE is not designed to work with hosted map services, or map service with read-only data source."
          + " The \"layers\" subresource returns all layers in the map service."
          + " The \"editFeature\" operation allows editing an existing annotation feature in the layer indicated by this SOE's layerId property.\n"
          + " The \"addFeatures\" operation allows addition of a new annotation feature to the layer indicated by this SOE's layerId property.\n"
          + " The acceptableEditSchema JSON indicates the correct schema that could be used to edit features. The geometry references in the schema refers to the textGraphic's geometry "
          + "as the polygon bounds or the feature will be automatically calculated. The example here shows a polyline geometry as this is most common. This field schema belongs to the layer "
          + "selected for editing by the ArcGIS Server administrator via the SOE's layerId property. This property's value can be "
          + "modified using ArcGIS Manager."
          + " The acceptableAddSchema JSON indicates the correct schema that could be used to add annotation features. This schema is specialized to pass in just a "
          + "text string and the font information for creation of the feature.");

      // validation - ensure user has provided right layer id property value.
      if (this.layerId > this.layerInfos.Count - 1)
      {
        return createErrorObject(406, "Layer Id " + this.layerId + " is invalid.", new String[] {
                    "Acceptable layer ids are between 0 and "
                        + (layerInfos.Count - 1) + ".",
                    "Also ensure that the id points to an annotation layer." });
      }

      // inform the user that edits can be done only on annotation layers, if no
      // annotation layer corresponds to user-provided layerId
      if (this.editLayerInfo == null)
      {
        this.editLayerInfo = this.layerInfos.get_Element(this.layerId);
        if (!(this.editLayerInfo.Type == "Annotation Layer"))
        {
          return createErrorObject(
                  403,
                  "The layerId property of this SOE currently points to a layer (id: "
                      + this.layerId
                      + ") that is not an annotation layer.",
                  new String[] {
                            "Only annotation layers can be edited by this SOE.",
                            "Modify SOE's layerId property using ArcGIS Manager or ArcGIS Desktop's Service Editor." });
        }
      }

      // Grab the fc powering the layer if its null, which means it did not get initialized in construct(), thereby 
      // suggesting that the layerId property value is incorrect.             
      if (this.fc == null)
      {
        // The down side of grabbing fc here is
        // that a new instance of fc is created once for every request.
        // Can't create fc in init(), since layerId property value for a
        // particular service is not necessarily available always when init() is invoked.	        
        this.fc = (IFeatureClass)this.mapServerDataAccess.GetDataSource(this.mapServerInfo.Name, this.layerId);
        if (this.fc == null)
        {
          // if its still null, return error
          return createErrorObject(
              406,
              "Incorrect layer id provided.",
              new String[] { "Please provide layer id of an annotation layer." });
        }
      }

      infoJSON.AddString("Layer selected for editing", editLayerInfo.Name.ToString() + " (" + layerId + ")");
      JsonObject schemaAdd = getSchemaAddJSON();
      infoJSON.AddObject("acceptableAddSchema", schemaAdd);
      JsonObject schemaEdit = getSchemaEditJSON();
      infoJSON.AddObject("acceptableEditSchema", schemaEdit);

      return Encoding.UTF8.GetBytes(infoJSON.ToJson());
    }

    private byte[] createErrorObject(int codeNumber, String errorMessageSummary, String[] errorMessageDetails)
    {
      if (errorMessageSummary.Length == 0 || errorMessageSummary == null)
      {
        throw new Exception("Invalid error message specified.");
      }

      JSONObject errorJSON = new JSONObject();
      errorJSON.AddLong("code", codeNumber);
      errorJSON.AddString("message", errorMessageSummary);

      if (errorMessageDetails == null)
      {
        errorJSON.AddString("details", "No error details specified.");
      }
      else
      {
        String errorMessages = "";
        for (int i = 0; i < errorMessageDetails.Length; i++)
        {
          errorMessages = errorMessages + errorMessageDetails[i] + "\n";
        }

        errorJSON.AddString("details", errorMessages);
      }

      JSONObject error = new JSONObject();
      error.AddJSONObject("error", errorJSON);

      return Encoding.UTF8.GetBytes(error.ToJSONString(null));
    }

    private byte[] LayersResHandler(NameValueCollection boundVariables, string outputFormat, string requestProperties, out string responseProperties)
    {
      responseProperties = null;

      CustomLayerInfo[] layerInfos = GetLayerInfos();

      JsonObject[] jos = new JsonObject[layerInfos.Length];

      for (int i = 0; i < layerInfos.Length; i++)
        jos[i] = layerInfos[i].ToJsonObject();

      JsonObject result = new JsonObject();
      result.AddArray("layersInfo", jos);

      string json = result.ToJson();

      return Encoding.UTF8.GetBytes(json);
    }

    private CustomLayerInfo[] GetLayerInfos()
    {
      int c = this.layerInfos.Count;

      CustomLayerInfo[] customLayerInfos = new CustomLayerInfo[c];

      for (int i = 0; i < c; i++)
      {
        IMapLayerInfo layerInfo = layerInfos.get_Element(i);
        customLayerInfos[i] = new CustomLayerInfo(layerInfo);
      }

      return customLayerInfos;
    }

    private byte[] addNewFeatureOperHandler(NameValueCollection boundVariables,
                                              JsonObject operationInput,
                                                  string outputFormat,
                                                  string requestProperties,
                                              out string responseProperties)
    {

      responseProperties = null;

      // get the feature JSON
      JsonObject newFeatureJSON = null;
      operationInput.TryGetJsonObject("featureJSON", out newFeatureJSON);

      // add the new feature
      IFeature newFeature;
      var bytes = addFeature(newFeatureJSON, out newFeature);

      if (null == newFeature)
        return bytes; //return error

      // send response back to client app
      var response = new JsonObject();
      response.AddString("status", "success");
      response.AddString("message", "Feature " + newFeature.OID + " added.");

      return Encoding.UTF8.GetBytes(response.ToJson());
    }

    private byte[] editFeatureOperHandler(NameValueCollection boundVariables,
                                              JsonObject operationInput,
                                                  string outputFormat,
                                                  string requestProperties,
                                              out string responseProperties)
    {
      responseProperties = null;

      // get the id of the feature to be edited	        
      object featureIdObj;
      operationInput.TryGetObject("featureId", out featureIdObj);
      int updateFeatureId = Convert.ToInt32(featureIdObj.ToString());

      object featureJSONObj;
      operationInput.TryGetObject("featureJSON", out featureJSONObj);
      JsonObject updateFeatureJSON = (JsonObject)featureJSONObj;

      // set a filter for the specific feature
      QueryFilter queryFilter = new QueryFilter();
      if (this.fc == null)
      {
        return createErrorObject(
                406,
                "Incorrect layer id provided.",
                new String[] { "Please provide layer id of an annotation layer." });
      }

      IClass myClass = (IClass)this.fc;
      queryFilter.WhereClause = myClass.OIDFieldName + "=" + updateFeatureId;

      IFeatureCursor featureCursor = this.fc.Search(queryFilter, false);

      // attempt retrieval of the feature and check if it does exist
      IFeatureCursor myFeatureCursor = (IFeatureCursor)featureCursor;
      IFeature updateFeature = myFeatureCursor.NextFeature();
      if (updateFeature == null)
      {
        return createErrorObject(
            406,
            "Incorrect feature id provided.",
            new String[] { "No feature exists for feature id "
                    + updateFeatureId + "." });
      }

      JsonObject response = new JsonObject();

      // edit feature
      string result = System.Text.Encoding.GetEncoding("utf-8").GetString(performEdits(updateFeature, updateFeatureJSON));
      if (result.Equals(System.Boolean.TrueString))
      {
        response.AddString("status", "success");
        response.AddString("message", "Feature " + updateFeatureId + " updated");
      }
      else
      {
        response.AddString("status", "failure");
        response.AddString("message", result);
      }

      // send response back to client app
      return Encoding.UTF8.GetBytes(response.ToJson());
    }

    /**
     * Performs edits to the geodatabase powering the map service that this SOE
     * extends
     * 
     * @param feature
     * @param featureJSON
     * @throws Exception
     */
    private byte[] performEdits(IFeature feature, JsonObject featureJSON)
    {
      IDataset fsDataset = (IDataset)this.fc;
      IWorkspace ws = fsDataset.Workspace;
      IWorkspaceEdit wsEdit = (IWorkspaceEdit)ws;
      try
      {
        // start an edit transaction to add a new feature to feature class
        wsEdit.StartEditing(false);
        wsEdit.StartEditOperation();

        // set attributes
        if (this.editLayerInfo == null)
        {
          this.editLayerInfo = this.layerInfos.get_Element(this.layerId);
          if (!this.editLayerInfo.IsFeatureLayer)
          {
            return createErrorObject(
                    403,
                    "The layerId property of this SOE currently points to a layer (id: "
                        + this.layerId
                        + ") that is not an annotation layer.",
                    new String[] {
                            "Only annotation layers can be edited by this SOE.",
                            "Modify SOE's layerId property using ArcGIS Manager." });
          }
        }

        IFields fields = this.editLayerInfo.Fields;

        JsonObject attributesJSON = null;
        featureJSON.TryGetJsonObject("attributes", out attributesJSON);

        System.Collections.IEnumerator itKeys = attributesJSON.GetEnumerator();
        while (itKeys.MoveNext())
        {
          KeyValuePair<string, object> kv = (KeyValuePair<string, object>)itKeys.Current;
          String key = kv.Key;
          int fieldId = fields.FindField(key);
          IField field = fields.get_Field(fieldId);

          object fieldValue = null;
          if (field.Editable)
          {
            //not using specific types based on field type, since can't assign value of any type to C# object
            attributesJSON.TryGetObject(key, out fieldValue);

            // set attribute field value
            feature.set_Value(fieldId, fieldValue);
          }
        }

        //commit attribute updates before the geometry change
        feature.Store();

        // retrieve geometry as json and convert it to ArcObject geometry
        JsonObject geometryJSON = null;
        featureJSON.TryGetJsonObject("geometry", out geometryJSON);

        IJSONConverterGeometry iConverter = new JSONConverterGeometryClass();
        IJSONObject obj = new JSONObjectClass();
        obj.ParseString(geometryJSON.ToJson());

        IGeometry geometry = iConverter.ToGeometry(obj, esriGeometryType.esriGeometryAny, false, false);

        // set geometry
        IMEAnnotationFeatureBridge featureBridge = feature as IMEAnnotationFeatureBridge;
        CIMTextGraphic cimTextGraphic = CIMTextGraphic.FromJson(featureBridge.AnnotationJSON);
        cimTextGraphic.Shape = geometry;
        featureBridge.AnnotationJSON = cimTextGraphic.ToJson();

        // store feature in feature class with geometry updates
        feature.Store();

        // end edit transaction
        wsEdit.StopEditOperation();
        wsEdit.StopEditing(true);
      }
      catch (Exception e)
      {
        if (wsEdit != null && wsEdit.IsBeingEdited())
        {
          wsEdit.StopEditing(false);
        }
        return createErrorObject(500,
            "Error occured while editing layer " + this.layerId + ".",
            new String[] { "Error details:", e.Message });
      }

      return Encoding.UTF8.GetBytes(System.Boolean.TrueString);
    }

    /**
     * Performs edits to the geodatabase powering the map service that this SOE
     * extends
     * 
     * @param feature
     * @param featureJSON
     * @throws Exception
     */
    private byte[] addFeature(JsonObject featureJSON, out IFeature feature)
    {
      feature = null;
      IDataset fsDataset = (IDataset)this.fc;
      IWorkspace ws = fsDataset.Workspace;
      IWorkspaceEdit wsEdit = (IWorkspaceEdit)ws;
      String textValue, fontFamilyValue, fontStyleValue;
      long symbolID = 0;
      CIMTextSymbol cimTextSymbol = null;
      try
      {
        // start an edit transaction to add a new feature to feature class
        wsEdit.StartEditing(false);
        wsEdit.StartEditOperation();

        feature = fc.CreateFeature();
        int statusIdx = feature.Fields.FindField("Status");

        IMEAnnotationFeatureClassExtensionBridge featureClassExt = fc.Extension as IMEAnnotationFeatureClassExtensionBridge;
        if (featureClassExt == null)
        {
        //TODO error
        }

        CIMSymbolIdentifier[] symbolIdents = CIMObject.ArrayFromJson(featureClassExt.SymbolCollectionJSON) as CIMSymbolIdentifier[];
        
        if (symbolIdents != null && symbolIdents.Length > 0)
        {
          symbolID = symbolIdents[0].ID;
          cimTextSymbol = symbolIdents[0].Symbol as CIMTextSymbol;
        }

        // set attributes
        if (this.editLayerInfo == null)
        {
          this.editLayerInfo = this.layerInfos.get_Element(this.layerId);
          if (!(this.editLayerInfo.Type == "Annotation Layer"))
          {
            return createErrorObject(
                    403,
                    "The layerId property of this SOE currently points to a layer (id: "
                        + this.layerId
                        + ") that is not an annotation layer.",
                    new String[] {
                            "Only annotation layers can be edited by this SOE.",
                            "Modify SOE's layerId property using ArcGIS Manager." });
          }
        }

        IFields fields = this.editLayerInfo.Fields;

        JsonObject attributesJSON = null;
        featureJSON.TryGetJsonObject("attributes", out attributesJSON);

        attributesJSON.TryGetString("text", out textValue);
        attributesJSON.TryGetString("fontFamily", out fontFamilyValue);
        attributesJSON.TryGetString("fontStyle", out fontStyleValue);

        // retrieve geometry as json and convert it to ArcObject geometry
        JsonObject geometryJSON = null;
        featureJSON.TryGetJsonObject("geometry", out geometryJSON);

        IJSONConverterGeometry iConverter = new JSONConverterGeometryClass();
        IJSONObject obj = new JSONObjectClass();
        obj.ParseString(geometryJSON.ToJson());

        IGeometry geometry = iConverter.ToGeometry(obj, esriGeometryType.esriGeometryAny, false, false);
        if (geometry == null)
        {
          return createErrorObject(
          400,
          "Unable to process input geometry: " + geometryJSON, new String[] {"Input a valid annotation feature geometry" });
        }

        //Create cimGraphic
        CIMTextGraphic textGraphic = new CIMTextGraphic();
        textGraphic.Shape = geometry;
        textGraphic.Text = textValue;

        CIMSymbolReference symbolReference = new CIMSymbolReference();
        symbolReference.SymbolName = symbolID.ToString();
        symbolReference.Symbol = cimTextSymbol;
        textGraphic.Symbol = symbolReference;

        //set the Text graphic into the anno feature
        IMEAnnotationFeatureBridge featureBridge = feature as IMEAnnotationFeatureBridge;
        feature.set_Value(statusIdx, 0); // set status to placed
        featureBridge.AnnotationJSON = textGraphic.ToJson();  // set graphic into annotation feature as JSON

        // store feature in feature class
        feature.Store();

        // end edit transaction
        wsEdit.StopEditOperation();
        wsEdit.StopEditing(true);
      }
      catch (Exception e)
      {
        if (wsEdit != null && wsEdit.IsBeingEdited())
        {
          wsEdit.StopEditing(false);
        }
        feature = null;
        return createErrorObject(500,
            "Error occured while editing layer " + this.layerId + ".",
            new String[] { "Error details:", e.Message });
      }

      return Encoding.UTF8.GetBytes(System.Boolean.TrueString);
    }


    // Retrieves feature schema for selected layer that could be used to provide data for editing.
    private JsonObject getSchemaEditJSON()
    {
      Fields fields = (Fields)editLayerInfo.Fields;
      int fieldCount = fields.FieldCount;

      JsonObject attributeJsonObject = new JsonObject();
      for (int i = 0; i < fieldCount; i++)
      {
        Field field = (Field)fields.get_Field(i);
        String typeStr = GetFieldTypeAsString(field);

        if (typeStr != null && typeStr.Length > 0 && field.Editable)
        {
          attributeJsonObject.AddString(field.Name, typeStr);
        }
      }

      JsonObject featuresJsonObject = new JsonObject();
      featuresJsonObject.AddJsonObject("attributes", attributeJsonObject);

      JsonObject geometryJson = new JsonObject();

      geometryJson.AddString("hasM", "false");
      geometryJson.AddString("hasZ", "false");
      geometryJson.AddString("paths", "["
          + "[ [x11, y11], [x12, y12] ],"
          + "[ [x21, y21], [x22, y22] ]]");

      JsonObject srJson = new JsonObject();
      srJson.AddString("wkid", "wkid");

      geometryJson.AddJsonObject("spatialReference", srJson);

      featuresJsonObject.AddJsonObject("geometry", geometryJson);

      return featuresJsonObject;
    }
    // Retrieves feature schema for selected layer that could be used to provide data for editing.
    private JsonObject getSchemaAddJSON()
    {
      JsonObject attributeJsonObject = new JsonObject();
      attributeJsonObject.AddString("text", "String");
      attributeJsonObject.AddString("fontFamilyName", "String");
      attributeJsonObject.AddString("fontStyle", "String");

      JsonObject featuresJsonObject = new JsonObject();
      featuresJsonObject.AddJsonObject("attributes", attributeJsonObject);

      JsonObject geometryJson = new JsonObject();

      geometryJson.AddString("hasM", "false");
      geometryJson.AddString("hasZ", "false");
      geometryJson.AddString("paths", "["
          + "[ [x11, y11], [x12, y12] ],"
          + "[ [x21, y21], [x22, y22] ]]");


      JsonObject srJson = new JsonObject();
      srJson.AddString("wkid", "wkid");

      geometryJson.AddJsonObject("spatialReference", srJson);

      featuresJsonObject.AddJsonObject("geometry", geometryJson);

      return featuresJsonObject;
    }

    private static string GetFieldTypeAsString(Field field)
    {
      string typeStr = null;
      switch (field.Type)
      {
        case esriFieldType.esriFieldTypeBlob:
          typeStr = "Blob";
          break;

        case esriFieldType.esriFieldTypeDate:
          typeStr = "Date";
          break;

        case esriFieldType.esriFieldTypeDouble:
          typeStr = "Double";
          break;

        case esriFieldType.esriFieldTypeInteger:
          typeStr = "Integer";
          break;

        case esriFieldType.esriFieldTypeRaster:
          typeStr = "Raster";
          break;

        case esriFieldType.esriFieldTypeSmallInteger:
          typeStr = "Integer";
          break;

        case esriFieldType.esriFieldTypeString:
          typeStr = "String";
          break;

        case esriFieldType.esriFieldTypeXML:
          typeStr = "XML";
          break;

        default:
          break;
      }

      return typeStr;
    }
  }
}
