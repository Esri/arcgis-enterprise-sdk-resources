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

import com.esri.interceptor.constants.Operation;
import com.esri.interceptor.helpers.Enrich;
import jakarta.servlet.ServletException;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Interceptor(
		name = "Geocoding",
		displayName = "Geocoding Interceptor",
		description = "Geocoding Interceptor",
		urlPatterns = {"Hosted/incidents/FeatureServer/0"},
		properties = {"prop1=value1"},
		chainingOrder = 1
)
public class Geocoding implements IServerServicesInterceptor {
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
		final GeoServiceInterceptorRequest serviceRequestWrapper = new GeoServiceInterceptorRequest(request);

		// Fetch the operation name
		final String operationName = interceptorHelper.getOperationName(serviceRequestWrapper, response);

		// Intercept edit operations of hosted feature service
		if (Operation.APPLY_EDITS.equalsIgnoreCase(operationName)) {
			Map<String, String[]> parameterMap = serviceRequestWrapper.getParameterMap();

			// Extract the edit operation parameters and update the values
			if (parameterMap.containsKey(Operation.ADDS)) {
				String[] addsValues = parameterMap.get(Operation.ADDS);
				if (addsValues != null && addsValues.length > 0) {
					String addsJsonString = URLDecoder.decode(addsValues[0], StandardCharsets.UTF_8);
					JSONArray addsArray = new JSONArray(addsJsonString);

					// Validate the input location. Add the missing address location by reverse geocoding
					Enrich.appendLocationInformation(addsArray);

					// Update the applyEdits operation request payload
					String modifiedAddsJson = addsArray.toString();
					if (modifiedAddsJson != null) {
						serviceRequestWrapper.updateParameter(Operation.ADDS, modifiedAddsJson);
					}
				}
			}
		}

		// Downstream runs and writes into our shared buffer
		filterChain.intercept(serviceRequestWrapper, response);
	}

	/*
	 * Perform any cleanup required by the interceptor at the end of its lifecycle
	 */
	@Override
	public void cleanup() {

	}

}
