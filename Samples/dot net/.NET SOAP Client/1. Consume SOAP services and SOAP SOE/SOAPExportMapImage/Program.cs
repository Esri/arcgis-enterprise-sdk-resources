using SOAPExportMapImage.SampleServer6;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SOAPExportMapImage
{
    class Program
    {
        static void Main(string[] args)
        {
            SampleServer6.USA_MapServer mapservice = new SampleServer6.USA_MapServer();
            String defaultMapName = mapservice.GetDefaultMapName();
            Console.WriteLine("Map name: " + defaultMapName + "\n");

            MapServerInfo mapinfo = mapservice.GetServerInfo(defaultMapName);
            MapDescription mapdesc = mapinfo.DefaultMapDescription;
            ImageType imgtype = new ImageType();
            imgtype.ImageFormat = esriImageFormat.esriImagePNG;
            imgtype.ImageReturnType = esriImageReturnType.esriImageReturnURL;
            ImageDisplay imgdisp = new ImageDisplay();
            imgdisp.ImageHeight = 500; //pixels
            imgdisp.ImageWidth = 500; //pixels
            imgdisp.ImageDPI = 96;
            ImageDescription imgdesc = new ImageDescription();
            imgdesc.ImageDisplay = imgdisp;
            imgdesc.ImageType = imgtype;
            MapImage mapimg = mapservice.ExportMapImage(mapdesc, imgdesc);
            Console.WriteLine("Output URL:" + mapimg.ImageURL);
            Console.ReadLine();
        }
    }
}
