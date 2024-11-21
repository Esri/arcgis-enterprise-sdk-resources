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
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net.Security;
using System.Net;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Windows.Forms;
using NetSimpleSoapSOAPClient.localhost;

namespace NetSimpleSoapSOAPClient
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();            
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                //create instance of proxy                   

                var echoService = new SampleWorldCities_NetSimpleSoapSOE();
                echoService.Url = "https://localhost:6443/arcgis/services/SampleWorldCities/MapServer/NetSimpleSoapSOE";



                // TODO REMOVE FOR PRODUCTION
                // This code disables HTTPS errors for self-serve certificates
                ServicePointManager.ServerCertificateValidationCallback = delegate (
               Object obj, X509Certificate certificate, X509Chain chain,
               SslPolicyErrors errors)
                {
                    return (true);
                };



                label2.Text = echoService.EchoInput(textBox1.Text);
            }
            catch (Exception ex)
            {
                label2.Text = ex.Message;
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}
