﻿// Copyright 2018 ESRI
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
using System.Text;
using ESRI.ArcGIS.esriSystem;
using ESRI.ArcGIS.Geometry;
using ESRI.ArcGIS.Carto;
using ESRI.Server.SOESupport;

namespace NetFindNearFeaturesRESTSOE
{
  public class CustomLayerInfo
  {
    public string Name { get; set; }
    public int ID { get; set; }
    public IEnvelope Extent { get; set; }

    public CustomLayerInfo(IMapLayerInfo mapLayerInfo)
    {
      this.Name = mapLayerInfo.Name;
      this.ID = mapLayerInfo.ID;
      this.Extent = mapLayerInfo.Extent;
    }

    public JsonObject ToJsonObject()
    {
      byte[] jsonBytes = Conversion.ToJson((IGeometry)this.Extent);
      JsonObject env = new JsonObject(Encoding.UTF8.GetString(jsonBytes));

      JsonObject jo = new JsonObject();
      jo.AddString("name", Name);
      jo.AddLong("id", ID);
      jo.AddJsonObject("extent", env);

      return jo;
    }
  }
}
