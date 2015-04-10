package com.jbetfair.entities;

import com.jbetfair.enums.RollupModel;

public class ExBestOfferOverRides {

  private int bestPricesDepth;
  private RollupModel rollupModel;
  private Integer rollupLimit;

  public static ExBestOfferOverRides defaultExBestOfferOverRides() {
    ExBestOfferOverRides e = new ExBestOfferOverRides();
    e.setBestPricesDepth(3);
    e.setRollupModel(RollupModel.STAKE);
    e.setRollupLimit(null);
    return e;
  }

  public int getBestPricesDepth() {
    return bestPricesDepth;
  }

  public void setBestPricesDepth(int bestPricesDepth) {
    this.bestPricesDepth = bestPricesDepth;
  }

  public RollupModel getRollupModel() {
    return rollupModel;
  }

  public void setRollupModel(RollupModel rollupModel) {
    this.rollupModel = rollupModel;
  }

  public Integer getRollupLimit() {
    return rollupLimit;
  }

  public void setRollupLimit(Integer rollupLimit) {
    this.rollupLimit = rollupLimit;
  }

}
