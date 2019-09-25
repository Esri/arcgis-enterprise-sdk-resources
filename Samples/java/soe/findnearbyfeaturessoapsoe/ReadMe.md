---
order: 7
---

# Java find nearby features SOAP SOE
This sample illustrates how to develop a SOAP server object extension (SOE) with a new spatial query method called ***findNearbyFeatures***. This SOAP method takes in a layer Id, a location point (x, y coordinates and spatial reference) and the distance to indicate how wide a net should be cast, and returns a list of all features within that distance.

Deploying the SOE from the .soe file (`../findnearbyfeaturessoapsoe/lib/JavaFindNearbyFeaturesSOAPSOE_ent.soe`) does not require you to open the IDE. However, you can load the project (`../findnearbyfeaturessoapsoe`) in Eclipse to debug, modify, and export the SOE project.

## Features
  * SOAP SOE interface
  * Create SOAP SOE methods
  * Spatial query

## Sample data
  Any dynamic map service published from ArcGIS Pro. This instruction uses the [USA map service](../../../ReadMe.md#1-usa-service) as the sample service to test with the SOE.

## Instructions

### Deploy the SOE

1. Log in to ArcGIS Server Manager and click the ***Site*** tab.
2. Click ***Extensions***.
3. Click ***Add Extension***.
4. Click ***Choose File*** and choose the ***JavaFindNearbyFeaturesSOAPSOE_ent.soe*** file (`../findnearbyfeaturessoapsoe/lib/JavaFindNearbyFeaturesSOAPSOE_ent.soe` or the exported SOE).
5. Click ***Add***.

### Enable the SOE on a map service

1. Make sure you have published the USA map service using ArcGIS Pro. If not, refer to [USA map service](../../../ReadMe.md#1-usa-service)
2. Log in to ArcGIS Server Manager and click the ***Services*** tab. Select USA map service and select ***Capabilities***.
3. In the list of available capabilities, find ***Java Find Nearby Features SOAP SOE*** and check the box to enable it.
4. Click the ***Save and Restart*** button to restart the service.

### Test the SOE by sending XML requests in SOAP clients
To test the ***findNearbyFeatures*** operation, try with this request:

   ``` xml
	<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
	xmlns:soe="http://www.soe.entsdksamples" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
	xmlns:esri="http://www.esri.com/schemas/ArcGIS/2.5.0">
	   <soapenv:Header/>
	   <soapenv:Body>
	      <soe:findNearbyFeatures>
		 <arg0>0</arg0>
		 <arg1 xsi:type="esri:PointN">
			 <X>-75</X>
			 <Y>40</Y>
		 </arg1>
		 <arg2>2</arg2>
	      </soe:findNearbyFeatures>
	   </soapenv:Body>
	</soapenv:Envelope>
   ```
