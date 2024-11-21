// Copyright 2023 ESRI
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
using ESRI.Server.SOESupport.SOI;

//This is SOI template of Enterprise SDK

namespace ClippingSOI
{
  [ComVisible(true)]
  [Guid("59f7eadf-1e00-4f0f-841b-c5815e1c0391")]
  [ClassInterface(ClassInterfaceType.None)]
  [ServerObjectInterceptor("MapServer",
      Description = "",
      DisplayName = ".NET Clipping SOI",
      Properties = "",
      SupportsSharedInstances = true)]
  public class ClippingSOI : IServerObjectExtension, IRESTRequestHandler, IWebRequestHandler, IRequestHandler2
    {
    private string _soiName;
    private IServerObjectHelper _soHelper;
    private RestSOIHelper _restSOIHelper;

    public ClippingSOI()
    {
      _soiName = this.GetType().Name;
    }

    public void Init(IServerObjectHelper pSOH)
    {
      _soHelper = pSOH;
      _restSOIHelper = new RestSOIHelper(pSOH);
    }

    public void Shutdown()
    {
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
      responseProperties = null;

      // Find the correct delegate to forward the request too
      IRESTRequestHandler restRequestHandler = _restSOIHelper.FindRequestHandlerDelegate<IRESTRequestHandler>();
      if (restRequestHandler == null)
        return null;

      /*
      * Add code to manipulate REST requests here
      */
      //Note: if you want to have a clipping and spatial filter SOI for security reasons,
      //      you must have your own custom implementation for the Query operation. 
      //      Clipping and SpatialFilter are not support in a query operation
      if (operationName == "export" || operationName == "identify" || operationName == "find")
      {
        var joOperationInput = new JsonObject(operationInput);

        if (joOperationInput.Exists("clipping"))
          joOperationInput.Delete("clipping");

        var joSpatialFilter = new JsonObject();
        joSpatialFilter.AddArray("excludedLayers", new object[] {  });
        joSpatialFilter.AddString("geometryType", "esriGeometryPolygon");
        joSpatialFilter.AddJsonObject("geometry", CreateACircle());
        joOperationInput.AddJsonObject("clipping", joSpatialFilter);

        operationInput = joOperationInput.ToJson();
      }

      return restRequestHandler.HandleRESTRequest(
              Capabilities, resourceName, operationName, operationInput,
              outputFormat, requestProperties, out responseProperties);
    }

    private JsonObject CreateACircle()
    {
      string circleJs = "{\"spatialReference\":{\"wkid\":4269}, \"curveRings\": [[[-102, 41],{\"a\":[[-102, 41], [-104, 39], 0, 1]}]]}";
      IPolygon poly = ESRI.Server.SOESupport.Conversion.ToGeometry(circleJs, esriGeometryType.esriGeometryPolygon) as IPolygon;
      ((IPolycurve)poly).Densify(0.1, 0.1); //Densifying as ToJsonObject() can't jsonify any curves
      return ESRI.Server.SOESupport.Conversion.ToJsonObject(poly, true);
    }
        #endregion

        #region SOAP interceptors
        // SpatialFilters and Clipping are supported in SOAP API.
        // If you want to achieve similar functions for SOAP endpoint, you can implement the logic in the following methods.
        public byte[] HandleBinaryRequest(ref byte[] request)
        {
            IRequestHandler requestHandler = _restSOIHelper.FindRequestHandlerDelegate<IRequestHandler>();
            if (requestHandler != null)
            {
                return requestHandler.HandleBinaryRequest(request);
            }

            //Insert error response here.
            return null;
        }

        public string HandleStringRequest(string Capabilities, string request)
        {
            IRequestHandler requestHandler = _restSOIHelper.FindRequestHandlerDelegate<IRequestHandler>();
            if (requestHandler != null)
            {
                return requestHandler.HandleStringRequest(Capabilities, request);
            }

            //Insert error response here.
            return null;
        }

        public byte[] HandleBinaryRequest2(string Capabilities, ref byte[] request)
        {
            IRequestHandler2 requestHandler = _restSOIHelper.FindRequestHandlerDelegate<IRequestHandler2>();
            if (requestHandler != null)
            {
                return requestHandler.HandleBinaryRequest2(Capabilities, request);
            }

            //Insert error response here.
            return null;
        }
        #endregion

        #region OGC interceptors
        public byte[] HandleStringWebRequest(esriHttpMethod httpMethod, string requestURL, string queryString, string Capabilities, string requestData, out string responseContentType, out esriWebResponseDataType respDataType)
        {
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
        #endregion

    }
}
