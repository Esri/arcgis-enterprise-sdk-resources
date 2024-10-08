# CSV Provider Sample

Type          : Full Fetch

Geometry Type : Points

Data Source   : CSV file fetched by URL

Cache         : No

OBJECTID      : Uses idField metadata

Comments      : Point data for ficticious shoe stores in USA.

# Path Params

host : N/A

id   : my-data

# Service JSON

```json
{
  "serviceName": "csv-shoe-stores",
  "type": "FeatureServer",
  "description": "Point data for ficticious shoe stores in USA.",
  "capabilities": "Query",
  "provider": "CUSTOMDATA",
  "clusterName": "default",
  "minInstancesPerNode": 0,
  "maxInstancesPerNode": 0,
  "instancesPerContainer": 1,
  "maxWaitTime": 60,
  "maxStartupTime": 300,
  "maxIdleTime": 1800,
  "maxUsageTime": 600,
  "loadBalancing": "ROUND_ROBIN",
  "isolationLevel": "HIGH",
  "configuredState": "STARTED",
  "recycleInterval": 24,
  "recycleStartTime": "00:00",
  "keepAliveInterval": 1800,
  "private": false,
  "isDefault": false,
  "maxUploadFileSize": 0,
  "allowedUploadFileTypes": "",
  "properties": {
    "disableCaching": "true"
  },
  "jsonProperties": {
    "customDataProviderInfo": {
      "dataProviderName": "csv-provider",
      "dataProviderHost": "",
      "dataProviderId": "my-data"
    }
  },
  "extensions": [],
  "frameworkProperties": {},
  "datasets": []
}

```
