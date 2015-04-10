package com.jbetfair.api.responses;

public class LoginResponse {

  private String token;
  private String product;
  private String status;
  private String error;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  @Override
  public String toString() {
    return "LoginResponse [token=" + token + ", product=" + product + ", status=" + status + ", error=" + error + "]";
  }

}
