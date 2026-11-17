package com.esri.interceptor.samples;

/*
COPYRIGHT 1995-2026 ESRI
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

import com.esri.arcgis.enterprise.interceptor.Interceptor;
import com.esri.arcgis.enterprise.interceptor.server.IServerServicesInterceptor;
import com.esri.arcgis.enterprise.interceptor.IInterceptorRequest;
import com.esri.arcgis.enterprise.interceptor.IInterceptorResponse;
import com.esri.arcgis.enterprise.interceptor.server.IServerServicesInterceptorChain;
import com.esri.arcgis.enterprise.interceptor.server.IServerServicesInterceptorHelper;
import com.esri.arcgis.enterprise.interceptor.IInterceptorConfig;
import com.esri.arcgis.enterprise.interceptor.server.IServerInterceptorLogger;

import com.esri.interceptor.constants.Attributes;
import com.esri.interceptor.constants.Errors;
import com.esri.interceptor.constants.Formats;
import com.esri.interceptor.constants.Operation;
import com.esri.interceptor.helpers.Enrich;
import jakarta.servlet.ServletException;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Interceptor(
		name = "IncidentManagement",
		displayName = "Incident Management Interceptor",
		description = "Incident Management Interceptor",
		urlPatterns = {"Hosted/incidents/FeatureServer"},
		properties = {"prop1=value1"},
		chainingOrder = 1
)
public class IncidentManagement implements IServerServicesInterceptor {
	private IServerServicesInterceptorHelper interceptorHelper;

	@Override
	public void init(IInterceptorConfig interceptorConfig,
					 IServerServicesInterceptorHelper interceptorHelper,
					 IServerInterceptorLogger logger){
		this.interceptorHelper = interceptorHelper;
	}

	@Override
	public void intercept(
			IInterceptorRequest request,
			IInterceptorResponse response,
			IServerServicesInterceptorChain filterChain) throws IOException, ServletException {

		// Continue the chain if it's not REST request
		if (!filterChain.isRestRequestChain()) {
			filterChain.intercept(request, response);
			return;
		}

		// Wrap the request for modification
		final ServiceInterceptorRequest serviceRequestWrapper = new ServiceInterceptorRequest(request);

		// Wrap the response for modification
		final ServiceInterceptorResponse serviceResponseWrapper = new ServiceInterceptorResponse(response);;

		// Fetch the operation name using wrappers
		final String operationName = interceptorHelper.getOperationName(serviceRequestWrapper, serviceResponseWrapper);

		// Intercept edit operations of hosted feature service
		if (Operation.APPLY_EDITS.equalsIgnoreCase(operationName)) {
			Map<String, String[]> parameterMap = serviceRequestWrapper.getParameterMap();

			// Extract the edit operation parameters and update the values
			if (parameterMap.containsKey(Operation.ADDS)) {
				String[] addsValues = parameterMap.get(Operation.ADDS);
				if (addsValues != null && addsValues.length > 0) {
					String addsJsonString = URLDecoder.decode(addsValues[0], StandardCharsets.UTF_8);
					JSONArray addsArray = new JSONArray(addsJsonString);

					// From input location extract the location information
					// (Address, Street, City, Zipcode)
					// using ReverseGeocode API
					Enrich.appendLocationInformation(addsArray);

					// Update Incident ID and agency information from external system.
					Enrich.appendJSONStringRequest(addsArray);

					// Update the applyEdits operation request payload
					String modifiedAddsJson = addsArray.toString();
					if (modifiedAddsJson != null) {
						serviceRequestWrapper.updateParameter(Operation.ADDS, modifiedAddsJson);
					}
				}
			}
		}

		// Let inner chain write into our shared buffer
		filterChain.intercept(serviceRequestWrapper, serviceResponseWrapper);

		// Intercept only QUERY responses with objectIds
		if (Operation.QUERY.equalsIgnoreCase(operationName)) {
			Map<String, String[]> p = serviceRequestWrapper.getParameterMap();
			String f = first(p.get(Formats.FORMAT));   // may be null
			boolean isPbf = Formats.PBF.equalsIgnoreCase(f);
			boolean hasObjectIds = p.containsKey(Attributes.OBJECTIDS);
			boolean idsOnly  = "true".equalsIgnoreCase(first(p.get("returnIdsOnly")));
			boolean cntOnly  = "true".equalsIgnoreCase(first(p.get("returnCountOnly")));

			// Never touch id/count-only responses
			if (!(idsOnly || cntOnly)) {
				// Intercept only objectIds responses (your stated requirement)
				if (hasObjectIds) {
					if (isPbf) {
						byte[] enrichedPBF = Enrich.appendPBFResponse(serviceResponseWrapper.getResponseDataAsBytes(true));
						serviceResponseWrapper.sendResponse(response, enrichedPBF, true );
						return;
					} else if (Formats.JSON.equalsIgnoreCase(f) || Formats.PRETTYJSON.equalsIgnoreCase(f)) {
						// JSON enrichment (body → text, replace with gzip JSON)
						String original = Enrich.convertToString(serviceResponseWrapper.getResponseDataAsBytes(true), serviceResponseWrapper.getCharacterEncoding());
						String enriched = Enrich.appendJSONStringResponse(original);
						serviceResponseWrapper.sendResponse(response, enriched.getBytes(serviceResponseWrapper.getCharacterEncoding()), true );
						return; // we already wrote the response
					}
				}
			}
		}
		// Pass through for non-QUERY or no objectIds or other formats
		serviceResponseWrapper.sendResponse(response, null, false);
	}

  /*
   * Perform any cleanup required by the interceptor at the end of its lifecycle
  */
  @Override
  public void cleanup(){
    
  }

	private static String first(String[] a) {
		return (a != null && a.length > 0) ? a[0] : null;
	}
}
