package com.jbetfair.api.responses;

public class ListCountriesResult {

  private String countryCode;
  private int marketCount;

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public int getMarketCount() {
    return marketCount;
  }

  public void setMarketCount(int marketCount) {
    this.marketCount = marketCount;
  }

}
