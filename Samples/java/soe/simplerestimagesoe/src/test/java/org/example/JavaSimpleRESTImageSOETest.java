package org.example;


/*
COPYRIGHT 1995-2012 ESRI
TRADE SECRETS: ESRI PROPRIETARY AND CONFIDENTIAL
Unpublished material - all rights reserved under the
Copyright Laws of the United States and applicable international
laws, treaties, and conventions.

For additional information, contact:
Environmental Systems Research Institute, Inc.
Attn: Contracts and Legal Services Department
380 New York Street
Redlands, California, 92373
USA

email: contracts@esri.com
*/

import org.junit.Assert;
import org.junit.Test;

public class JavaSimpleRESTImageSOETest {

	//Test method for GetSchema
	@Test
	public void testGetSchema() throws Exception {
				JavaSimpleRESTImageSOE javaSimpleRESTImageSOE = new JavaSimpleRESTImageSOE();
		String schemaJSON = javaSimpleRESTImageSOE.getSchema();

		//Assert that the schema JSON is not null
		Assert.assertNotNull(schemaJSON);
	}

	//Test method for GetRootResource
	@Test
	public void testGetRootResource() throws Exception {
				JavaSimpleRESTImageSOE javaSimpleRESTImageSOE = new JavaSimpleRESTImageSOE();

		String capabilities = "";
		String resourceName = "";
		String operationName = "";
		String operationInput = "";
		String outputFormat = "json";
		String requestProperties = "";
		String[] responseProperties = new String[1];

		//Call handleRESTRequest
		byte[] response = javaSimpleRESTImageSOE.handleRESTRequest(capabilities,
		resourceName, operationName, operationInput,
		outputFormat, requestProperties,
		responseProperties);

		//Assert that the schema JSON is not null
		Assert.assertNotNull(response);
	}
}