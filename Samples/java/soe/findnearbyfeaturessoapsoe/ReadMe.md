---
order: 7
---

# Java find nearby features SOAP SOE
This sample illustrates how to develop a SOAP server object extension (SOE) with a new spatial query method called ***findNearbyFeatures***. This SOAP method takes in a layer Id, a location point (x, y coordinates and spatial reference) and the distance to indicate how wide a net should be cast, and returns a list of all features within that distance.

Deploying the SOE from the .soe file (`../findnearbyfeaturessoapsoe/target/findnearbyfeaturessoapsoe.soe`) does not require you to open a Java IDE. However, you can open the project (`../findnearbyfeaturessoapsoe`) in a Java IDE, such as Eclipse or IntelliJ, to debug, modify, and recompile the SOE code.

## Features
  * SOAP SOE interface
  * Create SOAP SOE methods
  * Spatial query

## Sample data
  Any dynamic map service published from ArcGIS Pro. This instruction uses the [USA map service](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/master/Samples) as the sample service to test with the SOE.

## Instructions

### Deploy the SOE

1. Log in to ArcGIS Server Manager and click the ***Site*** tab.
2. Click ***Extensions***.
3. Click ***Add Extension***.
4. Click ***Choose File*** and choose the ***findnearbyfeaturessoapsoe.soe*** file (`../findnearbyfeaturessoapsoe/target/findnearbyfeaturessoapsoe.soe`).
5. Click ***Add***.

### Enable the SOE on a map service

1. Make sure you have published the USA map service using ArcGIS Pro. If not, refer to [USA map service](https://github.com/Esri/arcgis-enterprise-sdk-resources/tree/master/Samples)
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
	xmlns:esri="http://www.esri.com/schemas/ArcGIS/2.6.0">
	   <soapenv:Header/>
	   <soapenv:Body>
	      <soe:findNearbyFeatures>
		 <arg0>0</arg0>
		 <arg1 xsi:type="esri:PointN">
			 <X>-149</X>
			 <Y>61</Y>
		 </arg1>
		 <arg2>1</arg2>
	      </soe:findNearbyFeatures>
	   </soapenv:Body>
	</soapenv:Envelope>
   ```

   And you should see the following response:

   ``` xml
	<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns1="http://www.soe.entsdksamples">
	<soap:Body>
		<ns1:findNearbyFeaturesResponse>
			<arg xsi:type="esri:RecordSet" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:esri="http://www.esri.com/schemas/ArcGIS/2.6.0">
				<Fields xsi:type="esri:Fields">
				<FieldArray xsi:type="esri:ArrayOfField">
					<Field xsi:type="esri:Field">
						<Name>OBJECTID</Name>
						<Type>esriFieldTypeOID</Type>
						<IsNullable>false</IsNullable>
						<Length>4</Length>
						<Precision>0</Precision>
						<Scale>0</Scale>
						<Required>true</Required>
						<Editable>false</Editable>
						<AliasName>OBJECTID</AliasName>
						<ModelName>OBJECTID</ModelName>
					</Field>
					<Field xsi:type="esri:Field">
						<Name>Shape</Name>
						<Type>esriFieldTypeGeometry</Type>
						<IsNullable>true</IsNullable>
						<Length>0</Length>
						<Precision>0</Precision>
						<Scale>0</Scale>
						<Required>true</Required>
						<GeometryDef xsi:type="esri:GeometryDef">
							<AvgNumPoints>0</AvgNumPoints>
							<GeometryType>esriGeometryPoint</GeometryType>
							<HasM>false</HasM>
							<HasZ>false</HasZ>
							<SpatialReference xsi:type="esri:GeographicCoordinateSystem">
							<WKT>GEOGCS["GCS_North_American_1983",DATUM["D_North_American_1983",SPHEROID["GRS_1980",6378137.0,298.257222101]],PRIMEM["Greenwich",0.0],UNIT["Degree",0.0174532925199433],AUTHORITY["EPSG",4269]]</WKT>
							<XOrigin>-400</XOrigin>
							<YOrigin>-400</YOrigin>
							<XYScale>999999999.99999988</XYScale>
							<ZOrigin>-100000</ZOrigin>
							<ZScale>10000</ZScale>
							<MOrigin>-100000</MOrigin>
							<MScale>10000</MScale>
							<XYTolerance>8.9831528411952133e-09</XYTolerance>
							<ZTolerance>0.001</ZTolerance>
							<MTolerance>0.001</MTolerance>
							<HighPrecision>true</HighPrecision>
							<LeftLongitude>-180</LeftLongitude>
							<WKID>4269</WKID>
							<LatestWKID>4269</LatestWKID>
							</SpatialReference>
							<GridSize0>1.0651718846888474</GridSize0>
						</GeometryDef>
						<AliasName>Shape</AliasName>
						<ModelName>Shape</ModelName>
					</Field>
					<Field xsi:type="esri:Field">
						<Name>AREANAME</Name>
						<Type>esriFieldTypeString</Type>
						<IsNullable>true</IsNullable>
						<Length>50</Length>
						<Precision>0</Precision>
						<Scale>0</Scale>
						<AliasName>AREANAME</AliasName>
					</Field>
					<Field xsi:type="esri:Field">
						<Name>CLASS</Name>
						<Type>esriFieldTypeString</Type>
						<IsNullable>true</IsNullable>
						<Length>30</Length>
						<Precision>0</Precision>
						<Scale>0</Scale>
						<AliasName>CLASS</AliasName>
					</Field>
					<Field xsi:type="esri:Field">
						<Name>ST</Name>
						<Type>esriFieldTypeString</Type>
						<IsNullable>true</IsNullable>
						<Length>2</Length>
						<Precision>0</Precision>
						<Scale>0</Scale>
						<AliasName>ST</AliasName>
					</Field>
					<Field xsi:type="esri:Field">
						<Name>CAPITAL</Name>
						<Type>esriFieldTypeString</Type>
						<IsNullable>true</IsNullable>
						<Length>1</Length>
						<Precision>0</Precision>
						<Scale>0</Scale>
						<AliasName>CAPITAL</AliasName>
					</Field>
					<Field xsi:type="esri:Field">
						<Name>POP2000</Name>
						<Type>esriFieldTypeInteger</Type>
						<IsNullable>true</IsNullable>
						<Length>4</Length>
						<Precision>0</Precision>
						<Scale>0</Scale>
						<AliasName>POP2000</AliasName>
					</Field>
				</FieldArray>
				</Fields>
				<Records xsi:type="esri:ArrayOfRecord">
				<Record xsi:type="esri:Record">
					<Values xsi:type="esri:ArrayOfValue">
						<Value xsi:type="xs:int">3554</Value>
						<Value xsi:type="esri:PointN">
							<X>-149.76209691699998</X>
							<Y>61.191899875000047</Y>
						</Value>
						<Value xsi:type="xs:string">Anchorage</Value>
						<Value xsi:type="xs:string">municipality</Value>
						<Value xsi:type="xs:string">AK</Value>
						<Value xsi:type="xs:string">N</Value>
						<Value xsi:type="xs:int">260283</Value>
					</Values>
				</Record>
				</Records>
			</arg>
		</ns1:findNearbyFeaturesResponse>
	</soap:Body>
	</soap:Envelope>
   ```
