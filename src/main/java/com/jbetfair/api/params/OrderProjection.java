package com.jbetfair.api.params;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum OrderProjection implements APIRequestParam {
  ALL, EXECUTABLE, EXECUTION_COMPLETE;

  public String getParameterName() {
    return "orderProjection";
  }

  public static Set<OrderProjection> all() {
    return new HashSet<OrderProjection>(Arrays.asList(OrderProjection.values()));

  }
}
