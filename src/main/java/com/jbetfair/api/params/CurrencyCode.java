package com.jbetfair.api.params;

public enum CurrencyCode implements APIRequestParam {
  EUR, GBP;

  public String getParameterName() {
    return "currencyCode";
  }
}
