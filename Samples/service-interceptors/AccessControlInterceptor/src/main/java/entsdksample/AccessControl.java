package entsdksample;

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
import java.util.*;

import entsdksample.helpers.Constants;
import entsdksample.helpers.StringUtils;

@Interceptor(
    name = "AccessControl",
    displayName = "Access Control Interceptor",
    description = "Access Control Interceptor",
    urlPatterns = {"Hosted/States/FeatureServer/0"},
    properties = {"prop1=value1"},
    chainingOrder = 1 )

public class AccessControl implements IServerServicesInterceptor {

  private IServerServicesInterceptorHelper interceptorHelper;
  private IServerInterceptorLogger logger;
  private Map<String, HashSet<String>> userAccessMap;
  HashSet<String> stateSet = null;

  @Override
  public void init(IInterceptorConfig interceptorConfig, IServerServicesInterceptorHelper interceptorHelper, IServerInterceptorLogger logger) {
    this.interceptorHelper = interceptorHelper;
    this.logger = logger;

    // Initialize user access mapping (once during interceptor init)
    userAccessMap = new HashMap<>();
    userAccessMap.put("james12", new HashSet<>(Arrays.asList("California", "Oregon")));
    userAccessMap.put("sam259", new HashSet<>(Arrays.asList("Washington", "New York")));
    userAccessMap.put("tony64", new HashSet<>(Arrays.asList("Texas", "Oklahoma")));
  }

  @Override
  public void intercept( IInterceptorRequest request, IInterceptorResponse response,
                         IServerServicesInterceptorChain filterChain) throws IOException, ServletException {

    // Continue the chain if it's not REST request
    if (!filterChain.isRestRequestChain()) {
      filterChain.intercept(request, response);
      return;
    }

    // Wrap the request for modification
    ServiceInterceptorRequest serviceRequestWrapper = new ServiceInterceptorRequest(request);

    // Retrieve the operation name
    final String operationName = interceptorHelper.getOperationName(serviceRequestWrapper, response);

    // Apply data filtering only for QUERY operation
    if (Constants.QUERY.equalsIgnoreCase(operationName)) {
      // Get current user info
      String userInfo = interceptorHelper.getUsername(serviceRequestWrapper, response);

      // Extract actual username from user info
      final String userName = StringUtils.extractUserName(StringUtils.safeString(userInfo));
      logger.info("AccessControl: resolved user '" + userName + "'");

      if (!userName.isEmpty()) {
        // Determine accessible states for the current user
        if (userAccessMap.containsKey(userName)) {
          stateSet = userAccessMap.get(userName);
        }
        logger.info(stateSet != null ? stateSet.toString() : "No information available");

        if (stateSet != null) {
          String whereclausename = Constants.WHERE_CLAUSE_PARAM_NAME;
          Map<String, String[]> params = serviceRequestWrapper.getParameterMap();
          if (params.containsKey(whereclausename)) {
            String existingWhere = params.get(whereclausename)[0];

            // Build the WHERE clause for accessible states
            String stateFilter = StringUtils.createStateWhereClause(stateSet); // e.g. STATE_NAME IN ('CA','OR')

            String newWhere;
            if (existingWhere == null || existingWhere.trim().isEmpty() ||
                existingWhere.equalsIgnoreCase("1=1")) {
              newWhere = stateFilter;
            } else {
              newWhere = "(" + existingWhere + ") AND (" + stateFilter + ")";
            }

            // Update the request with new WHERE clause
            serviceRequestWrapper.updateParameter(whereclausename, newWhere);
          }
        }
      }
    }

    // Continue the interceptor chain with modified request
    filterChain.intercept(serviceRequestWrapper, response);
  }

  /*
   * Perform any cleanup required by the interceptor at the end of its lifecycle
   */
  @Override public void cleanup() {
    stateSet = null;
    userAccessMap = null;
  }
}