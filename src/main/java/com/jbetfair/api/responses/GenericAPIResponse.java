package com.jbetfair.api.responses;

import java.util.List;

import com.jbetfair.exceptions.APIError;

public class GenericAPIResponse<T> {

  private String id;
  private List<T> result;
  private String jsonrpc;
  private APIError error;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<T> getResult() {
    return result;
  }

  public void setResult(List<T> result) {
    this.result = result;
  }

  public String getJsonrpc() {
    return jsonrpc;
  }

  public void setJsonrpc(String jsonrpc) {
    this.jsonrpc = jsonrpc;
  }

  public APIError getError() {
    return error;
  }

  public void setError(APIError error) {
    this.error = error;
  }

  public boolean hasError() {
    return this.error != null;
  }

}
