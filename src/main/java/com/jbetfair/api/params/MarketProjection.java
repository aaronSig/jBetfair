package com.jbetfair.api.params;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum MarketProjection implements APIRequestParam {
  COMPETITION, EVENT, EVENT_TYPE, MARKET_START_TIME, MARKET_DESCRIPTION, RUNNER_DESCRIPTION, RUNNER_METADATA;

  public String getParameterName() {
    return "marketProjection";
  }

  public static Set<MarketProjection> allProjections() {
    return new HashSet<MarketProjection>(Arrays.asList(MarketProjection.values()));
  }

}
