package com.jbetfair.api.params;

public enum MarketSort implements APIRequestParam {
  MINIMUM_TRADED, MAXIMUM_TRADED, MINIMUM_AVAILABLE, MAXIMUM_AVAILABLE, FIRST_TO_START, LAST_TO_START;

  public String getParameterName() {
    return "sort";
  };
}
