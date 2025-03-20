# Objective
This provider demonstrates how to read overture geoparquet for theme as "addresses"

This provider includes the sample code which connect to overture geoparquet file stored in AWS S3 for "addresses" theme.

## Details
This provider queries the data from AWS S3 bucket "s3://overturemaps-us-west-2/release/2025-02-19.0/theme=addresses/type=*/*" 

It uses the query below to retrieve the data 

```
SELECT
  CAST(row_number() OVER () AS INTEGER) AS OBJECTID,
  country,
  postcode,
  street,
  number,
  unit,
  CAST(address_levels[1].value AS VARCHAR(256)) AS address_level,
  postal_city,
  geometry
FROM read_parquet('s3://overturemaps-us-west-2/release/2025-02-19.0/theme=addresses/type=*/*', 
  filename=true, hive_partitioning=1)
WHERE 
  bbox.xmin > -114.305
	AND bbox.xmax < -113.784
  AND bbox.ymin > 50.854 
	AND bbox.ymax < 51.219
```

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