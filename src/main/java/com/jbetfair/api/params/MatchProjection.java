package com.jbetfair.api.params;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum MatchProjection implements APIRequestParam {
  NO_ROLLUP, ROLLED_UP_BY_PRICE, ROLLED_UP_BY_AVG_PRICE;

  public String getParameterName() {
    return "matchProjection";
  }

  public static Set<MatchProjection> all() {
    return new HashSet<MatchProjection>(Arrays.asList(MatchProjection.values()));
  }

}
