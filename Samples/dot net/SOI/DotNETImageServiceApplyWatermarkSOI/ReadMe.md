---
order: 20
---

# .NET apply watermark SOI for Image Service

This sample illustrates how to apply a watermark to image service returned from the [Export Map](https://developers.arcgis.com/rest/services-reference/export-map.htm) operation on a Image service.

You need to build soe with Visual Studio and then deploying the SOI from the .soe file (`..\bin\Debug\net8.0\win-x64\DotNetImageServiceApplyWatermarkSOI_ent.soe`). In addition, you can load the project (`..\NetApplyWatermarkSOI.csproj`) in Visual Studio to debug, modify, and recompile the SOI code.

## Features

* Postprocess REST response
* OutputFormat

## Sample data

Any Image service published from ArcGIS Pro or Admin rest end and geoprocessing tool. This instruction uses a Image Service as the sample service to test with the SOI.

## Instructions

### Deploy the SOI

1. Log in to ArcGIS Server Manager and click the ***Site*** tab.
2. Click ***Extensions***.
3. Click ***Add Extension***.
4. Click ***Choose File*** and choose the ***DotNetImageServiceApplyWatermarkSOI_ent.soe*** file (`..\bin\Debug\DotNetImageServiceApplyWatermarkSOI_ent.soe`).
5. Click ***Add***.

### Enable the SOI on a map service

1. Make sure you have published the Image service using ArcGIS Pro or Admin rest end and geoprocessing tool. 
2. Log in to ArcGIS portal home application and select the Image Servive.Click the ***Settings*** tab. Select Extensions and expand it and find the ***SOI*** and expand it.
3. In the ***SOI*** section, select ***DotNetImageServiceApplyWatermarkSOI_ent*** in the ***Available Interceptors*** box and enable it ***Enabled Interceptors***.
   ![](../../../../images/netsp/ImageServiceWaterMarkSOI1.png "Image Service Apply Watermark SOI")
4. Click the ***Save and Restart*** button to restart the service.

### Test the SOI

1. Open a browser and navigate to the REST services endpoint of the USA map service (URL: `http://<serverdomain>/<webadaptorname>/rest/services/Name/ImageServer`).
2. Scroll to the bottom of the above page and click ***Export Map*** in ***Supported Extensions***.

   This leads you to the following URL:

   ```
   http://<serverdomain>/<webadaptorname>/rest/services/Name/ImageServer/export?bbox=-178.85719640187426,13.522152002873426,-56.484036397641795,81.72479317856566
   ```
3. The watermark ***(c) ESRI Inc.*** will appear at the bottom left corner of the exported image.

   ![](../../../../images/netsp/NetWatermarkSOI2.png "Net Image Service Watermark SOI Sample")


