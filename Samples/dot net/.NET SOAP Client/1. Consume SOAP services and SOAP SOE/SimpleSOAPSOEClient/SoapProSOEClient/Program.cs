using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoapProSOEClient
{
    class Program
    {
        static void Main(string[] args)
        {
            SoapProSOE.SampleWorldCities_SoapProSOE1 soe = new SoapProSOE.SampleWorldCities_SoapProSOE1();
            SoapProSOE.PointN centerPoint = new SoapProSOE.PointN();
            centerPoint.X = 30;
            centerPoint.Y = 50;
            SoapProSOE.SpatialReference sr = new SoapProSOE.GeographicCoordinateSystem();
            sr.WKIDSpecified = true;
            sr.WKID = 4326;
            centerPoint.SpatialReference = sr;
            SoapProSOE.PolygonN polygon = soe.BufferGeometry(centerPoint, 5) as SoapProSOE.PolygonN;
        }
    }
}
