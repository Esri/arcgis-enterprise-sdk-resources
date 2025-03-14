# cdf-provider-duckdb
This provider is an exploratory effort into using ArcGIS Enterprise [custom data feeds](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/) with big data. 

This provider allows the ability to connect to external data sources (S3, GCS, AWS, Azure) using [DuckDB](https://duckdb.org/) and SQL queries. 

## See it in action with NY city taxi dataset and H3 bins (10 million points): 
- The following video shows two duckdb tables (within one duckdb database) exposed as feature services. CDFs allow you to expose multiple feature services in one provider by using the `default.json` file and url params.
- The first feature service is 10 million taxi points from a parquet file exposed at `/duckdb/rest/services/taxiParquet/FeatureServer/0`. 
- The second feature service is a delta table containing h3 bins to illustrate hot spots of taxi points exposed at `/duckdb/rest/services/deltaNYTaxiBins/FeatureServer/0`
- We manually set zoom filters on the features. The points only show at smaller zoom levels since its a dense dataset and the h3 bins only show at larger zoom levels.

https://github.com/user-attachments/assets/ddb26b4d-38f8-4958-8f86-78f03407621c

## Features
- configurable - add data sources that DuckDB supports 
- supports multiple DuckDB tables at once
- scalable - can support larger datasets
- supports `query` operation with `where` and `geometry` parameters

## Instructions
- See the most up to date official ArcGIS Enterprise [documentation](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/pass-through-custom-data-providers/) and [walkthrough](https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/create-a-yelp-custom-data-feed/)

## Quick Start 
- Install node.js and ArcGIS Enterprise SDK which comes with the custom data feed cli
- Create a cdf app with the cli `cdf createapp <app_name>`
- Copy the folder `providers/duckdb` from this repo to the providers directory in your app
- cd into `providers/duckdb` and do `npm install`
- cd back into your app base dir and do `npm run start` which should start your CDF app
- Routes should be displayed in the terminal and the duckdb feature serivce should be accessible at `http://127.0.0.1:8080/duckdb/rest/services/localParquet/FeatureServer/0`
- Open `frontend/index.html` in a browser for a js web map view of the CDF in action 

## Resources
- https://developers.arcgis.com/enterprise-sdk/
- https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/installing-and-configuring-custom-data-feeds/
- https://developers.arcgis.com/enterprise-sdk/guide/custom-data-feeds/
- https://enterprise.arcgis.com/en/server/latest/install/windows/welcome-to-the-arcgis-for-server-install-guide.htm
- https://www.esri.com/arcgis-blog/products/arcgis-enterprise/data-management/dev-summit-2023-custom-data-feeds/
- https://www.esri.com/arcgis-blog/products/arcgis-enterprise/developers/new-in-arcgis-enterprise-11-1-custom-data-feeds/
- https://youtu.be/0T9iF4FSoxs?si=9C5oBLcO-sw4m1-c
- https://mediaspace.esri.com/media/t/1_kk12l8t8
- https://koopjs.github.io/docs/available-plugins/providers
- https://github.com/koopjs/FeatureServer#featureserverroute
- https://github.com/koopjs/geoservice-utils
- https://developers.arcgis.com/rest/services-reference/enterprise/query-feature-service-layer/

## Issues

Find a bug or want to request a new feature?  Please let us know by submitting an issue.

## Contributing

Esri welcomes contributions from anyone and everyone. Please see our [guidelines for contributing](https://github.com/esri/contributing).

## Licensing

Copyright 2025 Esri

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

A copy of the license is available in the repository's [LICENSE.txt](LICENSE.txt?raw=true) file.
