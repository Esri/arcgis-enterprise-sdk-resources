using SOAPFeatureServiceQuery.SampleServer6;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SOAPFeatureServiceQuery
{
    class Program
    {
        static void Main(string[] args)
        {
            SampleServer6.Recreation_FeatureServer featureServer = new SampleServer6.Recreation_FeatureServer();
            int layerCount = featureServer.GetLayers(null).Count();
            Console.WriteLine("Layer count: " + layerCount.ToString());

            QueryFilter queryFilter = new QueryFilter();
            queryFilter.WhereClause = "1=1";
            ServiceDataOptions serviceDataOptions = new ServiceDataOptions();
            serviceDataOptions.Format = "NATIVE";
            ServiceData results = featureServer.Query(0, "1=1", queryFilter, serviceDataOptions,"",0);
            DataObjects recordObjects = results.Object as DataObjects;
            DataObject[] records = recordObjects.DataObjectArray;
            Console.WriteLine(records.Count().ToString() + " features are returned.");
            Console.ReadLine();
        }
    }
}
