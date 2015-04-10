package com.jbetfair.api.params;

public enum TimeGranularity implements APIRequestParam {
  DAYS, HOURS, MINUTES;

  public String getParameterName() {
    return "granularity";
  };

}
