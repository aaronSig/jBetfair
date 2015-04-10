package com.jbetfair.api.params;

import com.jbetfair.entities.ExBestOfferOverRides;
import com.jbetfair.enums.PriceData;

import java.util.Set;

public class PriceProjection implements APIRequestParam {

  private Set<PriceData> priceData;
  private ExBestOfferOverRides exBestOfferOverRides;
  private boolean virtualise;

  public static PriceProjection defaultPriceProjection() {
    PriceProjection p = new PriceProjection();
    p.setPriceData(PriceData.all());
    p.setExBestOfferOverRides(ExBestOfferOverRides.defaultExBestOfferOverRides());
    p.setVirtualise(false);
    return p;
  }

  public String getParameterName() {
    return "priceProjection";
  }

  public Set<PriceData> getPriceData() {
    return priceData;
  }

  public void setPriceData(Set<PriceData> priceData) {
    this.priceData = priceData;
  }

  public ExBestOfferOverRides getExBestOfferOverRides() {
    return exBestOfferOverRides;
  }

  public void setExBestOfferOverRides(ExBestOfferOverRides exBestOfferOverRides) {
    this.exBestOfferOverRides = exBestOfferOverRides;
  }

  public boolean isVirtualise() {
    return virtualise;
  }

  public void setVirtualise(boolean virtualise) {
    this.virtualise = virtualise;
  }

}
