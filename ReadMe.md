# arcgis-enterprise-sdk-resources

This repository provides samples and code snippets for server object extensions (SOEs), server object interceptors (SOIs) and custom data feeds (CDF) built with **11.5** ArcGIS Enterprise SDK. Learn more about [ArcGIS Enterprise SDK](https://developers.arcgis.com/enterprise-sdk/).

> Note: If you want to download the SOE and SOI samples built with 11.5 or previous versions of ArcGIS Enterprise SDK, refer to [Releases](https://github.com/Esri/arcgis-enterprise-sdk-resources/releases).

An SOI is capable of intercepting REST, SOAP, and OGC requests and responses of a map service in the following way:
<p align="center">
  <img src="images/soi.PNG">
</p>

An SOE is capable of extending the functions of a map service in the following way:
<p align="center">
  <img src="images/soe.PNG">
</p>

To get started, visit [ArcGIS Enterprise SDK Overview](https://developers.arcgis.com/enterprise-sdk/) for SDK prerequisites, installation, guide, and more.

## Features
- [Samples](Samples)

## Instructions
1. Fork and then clone the repo. 
2. Run and try the samples.

## Requirements
* ArcGIS Enterprise (or a standalone ArcGIS Server)
* ArcGIS Enterprise SDK
* Node.js (Only required for CDF)
* IDE
   * Visual Studio for .NET (C# or VB) 
   * IntelliJ IDEA, Eclipse IDE, or other IDE for Java
   * Any IDE or text editor
* ArcGIS Pro (Not required for CDF))

   ArcGIS Pro is required to prepare or publish the service that the SOE or SOI can be enabled with. 
   
> **Note:** ArcGIS Enterprise SDK supports map services published from ArcGIS Pro, including their feature access and OGC capabilities, such as feature service, WMS service, and so on. ArcGIS Enterprise SDK currently does not support hosted feature service or other services. 

## Resources
* [ArcGIS Enterprise SDK Developer Guide](https://developers.arcgis.com/enterprise-sdk/)
* [ArcGIS Server - Extending services](https://enterprise.arcgis.com/en/server/latest/develop/windows/about-extending-services.htm)


## Issues
Find a bug or want to request a new feature?  Please let us know by submitting an issue or [contact Technical Support](https://support.esri.com/en/contact-tech-support).

## Contributing
Esri welcomes contributions from anyone and everyone. Please see our [guidelines for contributing](https://github.com/esri/contributing).

## Licensing
Copyright 2021 Esri

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

A copy of the license is available in the repository's [License.txt](https://github.com/hanhansun/arcgis-enterprise-sdk-samples/blob/master/License.txt) file.
