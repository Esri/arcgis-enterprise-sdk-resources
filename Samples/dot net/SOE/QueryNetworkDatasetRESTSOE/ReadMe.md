---
order: 12
---

# .NET Query Network Dataset REST SOE

This sample demonstrates how to use the network dataset API to query network elements within an extent and return information about them. The QueryNetworkDatasetRESTSOE server object extension has only one operation called “Query by extent”. It finds the streets within the extent, gets their network edges, and returns out the edge ID, the from and to junction IDs, and the TravelTime cost to traverse the edge.

The administrator that deploys this SOE must enable it on the map service with a network dataset layer in the map.

Deploying the SOE from the .soe file (`..\QueryNetworkDatasetRESTSOE\bin\Release\QueryNetworkDatasetRESTSOE_ent.soe) `) does not require you to open Visual Studio. However, you can load the project (`..\QueryNetworkDatasetRESTSOE\QueryNetworkDatasetRESTSOE.csproj`) in Visual Studio to debug, modify, and recompile the SOE code.

## Features

* Open a network dataset from a layer in the map
* Access properties on the network dataset (INetworkDataset)
* Query network elements stored in the network dataset (INetworkQuery)
* Access network edge and junction properties (INetworkEdge, INetworkJunction)
* Access network attribute values (INetworkAttribute)

## Sample data

This sample SOE works with any network dataset that has a source feature class named “Streets” and a cost attribute named “TravelTime”. The example uses Network Analyst tutorial data available in ArcGIS Online (https://www.arcgis.com/home/item.html?id=d6bd91b2fddc483b8ccbc66942db84cb). The network dataset used in this sample is the Streets_ND network dataset within the Transportation feature dataset in SanDiego.gdb. You can find SanDiego.gdb inside the Tutorial folder once you download the tutorial data.  


## Instructions

### Deploy the SOE

1. Log in to ArcGIS Server Manager and click the ***Site*** tab.
2. Click ***Extensions***.
3. Click ***Add Extension***.
4. Click ***Choose File*** and choose the ***QueryNetworkDatasetRESTSOE_ent.soe*** file (`..QueryNetworkDatasetRESTSOE\bin\Release\QueryNetworkDatasetRESTSOE_ent.soe`).
5. Click ***Add***.

### Enable the SOE on a map service

1. Make sure you have published a map service with the Streets_ND network dataset layer from the SanDeigo.gdb in the map using ArcGIS Pro. 
2. Log in to ArcGIS Server Manager and click the ***Services*** tab. Select the map service and select ***Capabilities***.
3. In the list of available capabilities, find ***QueryNetworkDatasetRESTSOE*** and check the box to enable it.
4. Click the ***Save and Restart*** button to restart the service.

### Test the SOE in the ArcGIS Server Services Directory

1. Open a browser and navigate to the REST services endpoint of the map service which you have enabled the ***QueryNetworkDatasetRESTSOE*** capability.
2. Scroll to the bottom of the above page and click ***QueryNetworkDatasetRESTSOE*** in ***Supported Extensions***.

   This leads to the SOE’s root page, at the following URL:
   ```
   http://<serverdomain>/<webadaptorname>/rest/services/<servicename>/MapServer/exts/QueryNetworkDatasetRESTSOE 
   ```
   
3. Click ***Query by extent*** on Supported Operations.

   For the ***Extent*** parameter, pass in the extent using the `xmin,ymin;xmax,ymax` format. Make sure your extent covers part of your network dataset. If you publish your map service using San Diego network dataset, you can pass `-117.131657,32.688146;-117.112703,32.704500` to the ***Extent*** parameter. 

   Change ***Format*** to json and click the ***Query by extent (Get)*** button. You will receive a response returning the network elements from the network dataset within the extent you specify. The array of json includes the IDs of the edges within the extent, the IDs of the junctions associated with each edge, and the TravelTime for each edge. 

   ``` json
   {
      "NetworkElements": [
         {
            "EdgeID": 940,
            "FromJunctionID": 929,
            "ToJunctionID": 926,
            "TravelTime": 0.0663
         },
         {
            "EdgeID": 941,
            "FromJunctionID": 929,
            "ToJunctionID": 927,
            "TravelTime": 0.072
         },
         …
      ]  
   }
   ```
   
   ```
