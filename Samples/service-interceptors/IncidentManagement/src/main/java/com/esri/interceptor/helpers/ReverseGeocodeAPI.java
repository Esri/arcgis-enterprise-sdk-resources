package com.esri.interceptor.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ReverseGeocodeAPI {
  private static final String ACCESS_TOKEN = "";
  private static final String API_URL = "";

  public static String retrieveDetails(String location) throws IOException {
    String apiUrl = API_URL + "location=" + location + "&token=" + ACCESS_TOKEN;
    return getApiResponse(apiUrl);
  }

  /**
   * Makes an HTTP GET request and reads the entire response at once.
   */
  private static String getApiResponse(String apiUrl) throws IOException {
    URL url = new URL(apiUrl);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");

    if (conn.getResponseCode() != 200) {
      System.out.println("Error: HTTP response code " + conn.getResponseCode());
      return null;
    }

    //Read the response in one go
    try (InputStream inputStream = conn.getInputStream()) {
      return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
  }

}

