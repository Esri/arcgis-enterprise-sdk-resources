﻿// Copyright 2023 ESRI
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

using ESRI.Server.SOESupport.SOI;

namespace NetLayerAccessSOI
{
    /// <summary>
    /// TODO
    /// </summary>
    class MyRESTHandlerOpCode : RestHandlerOpCode
    {
        /// <summary>
        /// TODO
        /// </summary>
        public static readonly MyRESTHandlerOpCode CustomOperationDemoOpCode = new MyRESTHandlerOpCode(999);

        /// <summary>
        /// TODO
        /// </summary>
        /// <param name="internalValue"></param>
        protected MyRESTHandlerOpCode(int internalValue) : base(internalValue) { }
    }
}
