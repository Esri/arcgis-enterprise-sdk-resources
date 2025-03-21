## Purpose
This custom data provider sample demonstrates how to read Overture map data for the **“buildings”** theme. 

[Overture](https://overturemaps.org/) provides free and open map data normalized to a [single schema](https://docs.overturemaps.org/schema/). The data is categorized into six [themes](https://docs.overturemaps.org/guides/):
- [Addresses](https://docs.overturemaps.org/guides/addresses/)
- [Buildings](https://docs.overturemaps.org/guides/buildings/)
- [Base](https://docs.overturemaps.org/guides/base/)
- [Division](https://docs.overturemaps.org/guides/divisions/)
- [Places](https://docs.overturemaps.org/guides/places/)
- [Transportation](https://docs.overturemaps.org/guides/transportation/)

This sample focuses on the **“buildings”** theme. Overture's buildings data is freely available on both Amazon S3 and Microsoft Azure Blob Storage at the following locations:

| Provider         | Location |
|-----------------|----------|
| **Amazon S3**       | `s3://overturemaps-us-west-2/release/2025-02-19.0/theme=buildings/type=building/*` |
| **Azure Blob Storage** | `https://overturemapswestus2.blob.core.windows.net/release/2025-02-19.0/theme=buildings/type=building/*` |

**Note:** You can change the date "2025-02-19.0" in the above URLs to view data for a different month.

Overture distributes its datasets in **GeoParquet**, a column-oriented spatial data format that is a backwards-compatible extension of **Apache Parquet**. Parquet (and GeoParquet) is optimized for "cloud-native" queries, allowing developers to efficiently fetch column "chunks" of cloud-hosted data using various tools. 

If you are new to GeoParquet, we recommend consulting the [GeoParquet Guide](https://guide.cloudnativegeo.org/geoparquet/).

The **addresses GeoParquet file** data columns can be found [here](https://docs.overturemaps.org/guides/buildings/#understanding-the-parquet-files). 

This sample utilizes **DuckDB**, a powerful analytics tool that enables querying remote files while downloading only the necessary data. **DuckDB 1.1.0 and later** supports reading and writing GeoParquet files.

## Details
This provider queries address data from the AWS S3 bucket:

```
s3://overturemaps-us-west-2/release/2025-02-19.0/theme=buildings/type=building/*
```

The following query retrieves the data:

```sql
SELECT
	CAST(row_number() OVER () AS INTEGER) AS OBJECTID,
	sources[1].dataset as source,
	subtype,
	class,
	level,
	has_parts,
	is_underground,
	height,
	num_floors,
	num_floors_underground,
	min_height,
	min_floor,
	facade_color,
	facade_material,
	roof_material,
	roof_shape,
	roof_direction,
	roof_orientation,
	roof_color,
	geometry
FROM read_parquet('s3://overturemaps-us-west-2/release/2025-02-19.0/theme=buildings/type=building/*', 
	filename=true, hive_partitioning=1)
WHERE 
	bbox.xmin > -114.305
	AND bbox.xmax < -113.784
	AND bbox.ymin > 50.854 
	AND bbox.ymax < 51.219;
```

This query retrieves **buildings data for Calgary, Canada, and the surrounding area**, returning approximately **437,604 records**.

![image](https://github.com/user-attachments/assets/ec2836fb-61a5-4cd6-92e0-54b65acd8010)


## Instructions
1. **Create a new custom data app**:
   ```sh
   cdf createapp overture-app
   ```
   Alternatively, you can use an existing custom data app.
2. **Create a provider inside the custom data app** (created in step 1). Copy and paste the **“buildings** folder inside the **“providers”** folder of the custom data app.
3. **Install required Node.js packages** by running the following command inside the `../../providers/buildings` directory:
   ```sh
   npm install
   ```
4. **Package the data provider** by running the following command in the **custom data app directory**:
   ```sh
   cdf export buildings
   ```

### Important Note
This provider uses the **DuckDB** Node.js package, which requires separate compilation for the target operating system. 
- If you plan to deploy this provider in a **Windows ArcGIS Enterprise/Server** environment, ensure that your development environment is **Windows**.
- If you plan to deploy this provider in a **Linux ArcGIS Enterprise/Server** environment, ensure that your development environment is **Linux**.

## Issues
Found a bug or want to request a new feature? Please let us know by submitting an issue.

## Contributing
Esri welcomes contributions from anyone and everyone. Please see our [guidelines for contributing](https://github.com/esri/contributing).

## Licensing
Copyright 2025 Esri

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
You may obtain a copy of the License at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and limitations under the License.

A copy of the license is available in the repository's [LICENSE.txt](LICENSE.txt?raw=true) file.
