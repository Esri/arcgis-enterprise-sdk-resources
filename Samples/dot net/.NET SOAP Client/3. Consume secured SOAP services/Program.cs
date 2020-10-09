using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Web.Script.Serialization;

namespace SecuredMapServiceSoapClient
{
    class Program
    {
        static string username = "user1";
        static string password = "user1";

        //Secured map service accessible to user1
        static string mapServiceUrl = "https://sampleserver6.arcgisonline.com/arcgis/services/USA_secure_user1/MapServer"; 

        static void Main(string[] args)
        {
            CatalogServer.Catalog myCatalog = new CatalogServer.Catalog();

            if (myCatalog.RequiresTokens())
            {
                string tokenServiceUrl = myCatalog.GetTokenServiceURL();
                string token = GetToken(tokenServiceUrl, username, password);
                ExportMapImage(mapServiceUrl, token);
            }
        }

        private static string GetToken(string url, string username, string password)
        {
            object tokenstr = "";
            var postData = string.Format("username={0}&password={1}&client=requestip&expiration=100&f=json", username, password);
            var data = Encoding.ASCII.GetBytes(postData);
            var request = (HttpWebRequest)WebRequest.Create(url);
            request.Method = "POST";
            request.ContentType = "application/x-www-form-urlencoded";
            request.ContentLength = postData.Length;

            using (var stream = request.GetRequestStream())
            {
                stream.Write(data, 0, data.Length);
            }

            var response = (HttpWebResponse)request.GetResponse();
            var rawResponse = new StreamReader(response.GetResponseStream()).ReadToEnd();

            if (rawResponse.Contains("error"))
            {
                Console.WriteLine("failed: " + rawResponse);
                throw new System.InvalidOperationException("fix the token generation process");
            }
            else
            {
                JavaScriptSerializer json = new System.Web.Script.Serialization.JavaScriptSerializer();
                Dictionary<string, object> jsonResponse = json.Deserialize<Dictionary<string, object>>(rawResponse);
                if (jsonResponse.ContainsKey("token"))
                    jsonResponse.TryGetValue("token", out tokenstr);
            }
            response.Close();
            return tokenstr.ToString();
        }
    
        private static void ExportMapImage(string mapServiceURL, string token)
        {

            //You can use the following as MapServer reference:
            //1. ESRI.ArcGIS.SOAP downloaded from SOAP SDK, or
            //2. The MapServer proxy class built by yourself, or
            //3. Public map service with the same schema as the secured map service
            MapServer.USA_MapServer mapServer = new MapServer.USA_MapServer();

            //Append token to the service URL
            mapServer.Url = mapServiceURL + "?token=" + token;
            var defaultMapName = mapServer.GetDefaultMapName();
            Console.WriteLine("Map name: " + defaultMapName + "\n");

            var servInfo = mapServer.GetServerInfo(defaultMapName);
            var maplyInfo = servInfo.MapLayerInfos;
            MapServer.ImageDescription imageDescription = new MapServer.ImageDescription();
            imageDescription.ImageDisplay = new MapServer.ImageDisplay()
            {
                ImageDPI = 96,
                ImageHeight = 500,
                ImageWidth = 500
            };
            imageDescription.ImageType = new MapServer.ImageType()
            {
                ImageFormat = MapServer.esriImageFormat.esriImageJPG,
                ImageReturnType = MapServer.esriImageReturnType.esriImageReturnURL
            };

            var exportImage = mapServer.ExportMapImage(servInfo.DefaultMapDescription, imageDescription);
            string outImageUrl = exportImage.ImageURL;
            Console.WriteLine("Export map image url: " + outImageUrl);
            Console.ReadLine();
        }
    
    }

}
