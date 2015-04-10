package com.jbetfair.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.jbetfair.JBetfair;
import com.jbetfair.api.params.APIRequestParam;
import com.jbetfair.exceptions.BetfairException;
import com.jbetfair.util.JsonConverter;
import com.jbetfair.util.JsonResponseHandler;
import com.jbetfair.util.JsonrpcRequest;

public class HttpUtil {

  private static final String APING_URL = "https://api.betfair.com/exchange/betting/";
  private static final String SPORTS_APING_V1_0 = "SportsAPING/v1.0/";
  private static final int HTTP_DEFAULT_TIMEOUT = 10000;

  public String makeRequest(String appKey, String sessionToken, ApiOperation operation, APIRequestParam... parameters) throws BetfairException {
    return this.makeRequest(appKey, sessionToken, operation, constructParams(parameters));
  }

  public String makeRequest(String appKey, String sessionToken, ApiOperation operation, Map<String, Object> parameters) throws BetfairException {
    parameters.put("locale", JBetfair.locale);

    String requestString;
    // Handling the JSON-RPC request
    JsonrpcRequest request = new JsonrpcRequest();
    request.setId("1");
    request.setMethod(HttpUtil.SPORTS_APING_V1_0 + operation);
    request.setParams(parameters);

    requestString = JsonConverter.convertToJson(request);
    if (JBetfair.isDebug())
      System.out.println(operation + " request: " + requestString);

    try {
      String response = this.sendPostRequest(appKey, sessionToken, requestString);

      if (JBetfair.isDebug())
        System.out.println(operation + " response: " + response);

      return response;
    } catch (IOException e) {
      throw new BetfairException("Unable to complete operation " + operation.toString(), e);
    }
  }

  private String sendPostRequest(String appKey, String sessionToken, String jsonRequest) throws IOException {
    String URL = HttpUtil.APING_URL + "json-rpc/v1";

    HttpPost post = new HttpPost(URL);
    String resp = null;
    post.setHeader("Content-Type", "application/json");
    post.setHeader("Accept", "application/json");
    post.setHeader("Accept-Charset", "UTF-8");
    post.setHeader("X-Application", appKey);
    post.setHeader("X-Authentication", sessionToken);

    post.setEntity(new StringEntity(jsonRequest, "UTF-8"));

    HttpClient httpClient = new DefaultHttpClient();

    HttpParams httpParams = httpClient.getParams();
    HttpConnectionParams.setConnectionTimeout(httpParams, HttpUtil.HTTP_DEFAULT_TIMEOUT);
    HttpConnectionParams.setSoTimeout(httpParams, HttpUtil.HTTP_DEFAULT_TIMEOUT);

    resp = httpClient.execute(post, new JsonResponseHandler());
    return resp;
  }

  private Map<String, Object> constructParams(APIRequestParam... parameters) {
    Map<String, Object> params = new HashMap<String, Object>();
    for (APIRequestParam param : parameters) {
      params.put(param.getParameterName(), param);
    }
    return params;
  }

}
