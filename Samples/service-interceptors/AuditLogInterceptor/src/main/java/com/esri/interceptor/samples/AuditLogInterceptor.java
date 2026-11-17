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

import jakarta.servlet.ServletException;
import java.io.IOException;

@Interceptor(
		name = "AuditLogInterceptor",
		displayName = "Audit Log Interceptor",
		description = "Audit Log Interceptor",
		urlPatterns = {"/MapServer/","/FeatureServer/"},
		properties = {"prop1=value1"},
		chainingOrder = 1
)
public class AuditLogInterceptor implements IServerServicesInterceptor {
	private IServerServicesInterceptorHelper interceptorHelper;
	private IServerInterceptorLogger logger;

	@Override
	public void init(IInterceptorConfig interceptorConfig,
									 IServerServicesInterceptorHelper interceptorHelper,
									 IServerInterceptorLogger logger){
		// This code executes at the time of registration
		this.interceptorHelper = interceptorHelper;
		this.logger = logger;
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

		//To intercept the request, write your code here
		filterChain.intercept(request, response);

		//To intercept the response, write your code here
		logUserAction(request, response);
	}

	/*
	 * Perform any cleanup required by the interceptor at the end of its lifecycle
	*/
	@Override
	public void cleanup() {

	}

	private void logUserAction(IInterceptorRequest request, IInterceptorResponse response) {
		String username       = interceptorHelper.getUsername(request, response);
		String serviceName    = interceptorHelper.getServiceName(request, response);
		String serviceType    = interceptorHelper.getServiceType(request, response);
		String serviceProvider= interceptorHelper.getServiceProviderType(request, response);
		String operationName  = interceptorHelper.getOperationName(request, response);

		String msg = "User '" + username + "' "
				+ "accessed the service " + serviceName + "." + serviceType + ". "
				+ "Service provider is " + serviceProvider + ". "
				+ ((operationName != null && !operationName.isEmpty())
				? "Performed the operation - '" + operationName + "'."
				: "");

		logger.info(msg);
	}
}
