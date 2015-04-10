package com.jbetfair.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum PriceData {
  SP_AVAILABLE, SP_TRADED, EX_BEST_OFFERS, EX_ALL_OFFERS, EX_TRADED;

  public static Set<PriceData> all() {
    return new HashSet<PriceData>(Arrays.asList(PriceData.values()));
  }

}
