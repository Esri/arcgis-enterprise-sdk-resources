// Copyright 2023 ESRI
// 
// All rights reserved under the copyright laws of the United States
// and applicable international laws, treaties, and conventions.
// 
// You may freely redistribute and use this sample code, with or
// without modification, provided you include the original copyright
// notice and use restrictions.
// 
// See the use restrictions at <your Enterprise SDK install location>/userestrictions.txt.
// 

using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Security;
using System.Net;
using System.Security.Cryptography.X509Certificates;
using System.Windows.Forms;

namespace NetFindNearFeaturesSOAPClient
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);


            // TODO REMOVE FOR PRODUCTION
            // This code disables HTTPS errors for self-serve certificates
            ServicePointManager.ServerCertificateValidationCallback = delegate (
           Object obj, X509Certificate certificate, X509Chain chain,
           SslPolicyErrors errors)
            {
                return (true);
            };


            Application.Run(new Form1());
        }
    }
}
