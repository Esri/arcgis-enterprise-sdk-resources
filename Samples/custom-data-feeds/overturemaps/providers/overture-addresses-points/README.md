## Purpose
This custom data provider sample demonstrates how to read Overture map data for the **“addresses”** theme. 

Overture provides free and open map data normalized to one schema. They have divided data into six themes:
- Addresses
- Buildings
- Base
- Division
- Places
- Transportation

This sample focuses on the **“addresses”** theme. Overture's address theme data is freely available on both Amazon S3 and Microsoft Azure Blob Storage at these locations:

| Provider         | Location |
|-----------------|----------|
| Amazon S3       | `s3://overturemaps-us-west-2/release/2025-03-19.0/theme=addresses/type=address/*` |
| Azure Blob Storage | `https://overturemapswestus2.blob.core.windows.net/release/2025-03-19.0/theme=addresses/type=address/*` |

Overture distributes its datasets as **GeoParquet**, a column-oriented spatial data format that is a backwards-compatible extension of **Apache Parquet**. Parquet (and GeoParquet) is optimized for "cloud-native" queries, which means you can use many developer-friendly tools to efficiently fetch column "chunks" of cloud-hosted data. We encourage users who are new to GeoParquet to consult this guide.

The **addresses GeoParquet file** data columns can be found [here](#). 

This sample uses **DuckDB**, a powerful analytics tool that allows you to query remote files and download only the data you want. **DuckDB 1.1.0 and onwards** supports reading and writing GeoParquet.

## Details
This provider queries the data from the AWS S3 bucket:

```
s3://overturemaps-us-west-2/release/2025-02-19.0/theme=addresses/type=*/*
```

It uses the query below to retrieve the data:

```sql
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
  AND bbox.ymax < 51.219;
```

This query will return **address data for Calgary, CA, and the surrounding area**. The number of records returned by the above query is approximately **587,258**.

## Instruction
1. Create a new custom data app:
   ```sh
   cdf createapp overture-app
   ```
   Or you can use an existing custom data app.
2. Create a provider inside the custom data app (created in step 1). You can copy and paste the same folder **“addresses”** inside the **“providers”** folder of the custom data app.
3. Install node packages by running the following command inside the directory `../../providers/addresses`:
   ```sh
   npm install

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
