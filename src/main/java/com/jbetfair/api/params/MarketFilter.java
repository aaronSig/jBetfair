package com.jbetfair.api.params;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.jbetfair.api.entities.EventTypes;
import com.jbetfair.entities.TimeRange;
import com.jbetfair.enums.MarketBettingType;
import com.jbetfair.enums.OrderStatus;

public class MarketFilter implements APIRequestParam {

  private String textQuery;
  private Set<String> exchangeIds;
  private Set<String> eventTypeIds;
  private Set<String> marketIds;
  private Boolean inPlayOnly;
  private Set<String> eventIds;
  private Set<String> competitionIds;
  private Set<String> venues;
  private Boolean bspOnly;
  private Boolean turnInPlayEnabled;
  private Set<MarketBettingType> marketBettingTypes;
  private Set<String> marketCountries;
  private Set<String> marketTypeCodes;
  private TimeRange marketStartTime;
  private Set<OrderStatus> withOrders;

  public String getParameterName() {
    return "filter";
  }

  public String getTextQuery() {
    return textQuery;
  }

  public void setTextQuery(String textQuery) {
    this.textQuery = textQuery;
  }

  public MarketFilter withTextQuery(String textQuery) {
    this.textQuery = textQuery;
    return this;
  }

  public Set<String> getExchangeIds() {
    return exchangeIds;
  }

  public void setExchangeIds(Set<String> exchangeIds) {
    this.exchangeIds = exchangeIds;
  }

  public MarketFilter withExchangeIds(Set<String> exchangeIds) {
    this.exchangeIds = exchangeIds;
    return this;

  }

  public Set<String> getEventTypeIds() {
    return eventTypeIds;
  }

  public void setEventTypeIds(Set<String> eventTypeIds) {
    this.eventTypeIds = eventTypeIds;
  }

  public MarketFilter withEventTypeIds(Set<String> eventTypeIds) {
    this.eventTypeIds = eventTypeIds;
    return this;
  }

  public MarketFilter withEventTypeId(int eventTypeId) {
    this.eventTypeIds = ImmutableSet.of(Integer.toString(eventTypeId));
    return this;
  }
  
  public MarketFilter withEventType(EventTypes eventType) {
    this.eventTypeIds = ImmutableSet.of(Integer.toString(eventType.getId()));
    return this;
  }

  public Set<String> getMarketIds() {
    return marketIds;
  }

  public void setMarketIds(Set<String> marketIds) {
    this.marketIds = marketIds;
  }

  public MarketFilter withMarketIds(Set<String> marketIds) {
    this.marketIds = marketIds;
    return this;
  }

  public Boolean getInPlayOnly() {
    return inPlayOnly;
  }

  public void setInPlayOnly(Boolean inPlayOnly) {
    this.inPlayOnly = inPlayOnly;
  }

  public MarketFilter withInPlayOnly(Boolean inPlayOnly) {
    this.inPlayOnly = inPlayOnly;
    return this;
  }

  public Set<String> getEventIds() {
    return eventIds;
  }

  public void setEventIds(Set<String> eventIds) {
    this.eventIds = eventIds;
  }

  public MarketFilter withEventIds(Set<String> eventIds) {
    this.eventIds = eventIds;
    return this;
  }

  public Set<String> getCompetitionIds() {
    return competitionIds;
  }

  public void setCompetitionIds(Set<String> competitionIds) {
    this.competitionIds = competitionIds;
  }

  public MarketFilter withCompetitionIds(Set<String> competitionIds) {
    this.competitionIds = competitionIds;
    return this;
  }

  public Set<String> getVenues() {
    return venues;
  }

  public void setVenues(Set<String> venues) {
    this.venues = venues;
  }

  public MarketFilter withVenues(Set<String> venues) {
    this.venues = venues;
    return this;

  }

  public Boolean getBspOnly() {
    return bspOnly;
  }

  public void setBspOnly(Boolean bspOnly) {
    this.bspOnly = bspOnly;
  }

  public MarketFilter withBspOnly(Boolean bspOnly) {
    this.bspOnly = bspOnly;
    return this;
  }

  public Boolean getTurnInPlayEnabled() {
    return turnInPlayEnabled;
  }

  public void setTurnInPlayEnabled(Boolean turnInPlayEnabled) {
    this.turnInPlayEnabled = turnInPlayEnabled;
  }

  public MarketFilter withTurnInPlayEnabled(Boolean turnInPlayEnabled) {
    this.turnInPlayEnabled = turnInPlayEnabled;
    return this;
  }

  public Set<MarketBettingType> getMarketBettingTypes() {
    return marketBettingTypes;
  }

  public void setMarketBettingTypes(Set<MarketBettingType> marketBettingTypes) {
    this.marketBettingTypes = marketBettingTypes;
  }

  public MarketFilter withMarketBettingTypes(Set<MarketBettingType> marketBettingTypes) {
    this.marketBettingTypes = marketBettingTypes;
    return this;
  }

  public MarketFilter withMarketBettingType(MarketBettingType marketBettingType) {
    this.marketBettingTypes = ImmutableSet.of(marketBettingType);
    return this;
  }

  public Set<String> getMarketCountries() {
    return marketCountries;
  }

  public void setMarketCountries(Set<String> marketCountries) {
    this.marketCountries = marketCountries;
  }

  public MarketFilter withMarketCountries(Set<String> marketCountries) {
    this.marketCountries = marketCountries;
    return this;
  }

  public MarketFilter withMarketCountry(String marketCountry) {
    this.marketCountries = ImmutableSet.of(marketCountry);
    return this;
  }

  public Set<String> getMarketTypeCodes() {
    return marketTypeCodes;
  }

  public void setMarketTypeCodes(Set<String> marketTypeCodes) {
    this.marketTypeCodes = marketTypeCodes;
  }

  public MarketFilter withMarketTypeCodes(Set<String> marketTypeCodes) {
    this.marketTypeCodes = marketTypeCodes;
    return this;
  }

  public MarketFilter withMarketTypeCode(String marketTypeCode) {
    this.marketTypeCodes = ImmutableSet.of(marketTypeCode);
    return this;
  }

  public TimeRange getMarketStartTime() {
    return marketStartTime;
  }

  public void setMarketStartTime(TimeRange marketStartTime) {
    this.marketStartTime = marketStartTime;
  }

  public MarketFilter withMarketStartTime(TimeRange marketStartTime) {
    this.marketStartTime = marketStartTime;
    return this;
  }

  public Set<OrderStatus> getWithOrders() {
    return withOrders;
  }

  public void setWithOrders(Set<OrderStatus> withOrders) {
    this.withOrders = withOrders;
  }

  public MarketFilter withWithOrders(Set<OrderStatus> withOrders) {
    this.withOrders = withOrders;
    return this;
  }

  public String toString() {
    return "{" + "" + "textQuery=" + getTextQuery() + "," + "exchangeIds=" + getExchangeIds() + "," + "eventTypeIds=" + getEventTypeIds() + "," + "eventIds="
        + getEventIds() + "," + "competitionIds=" + getCompetitionIds() + "," + "marketIds=" + getMarketIds() + "," + "venues=" + getVenues() + ","
        + "bspOnly=" + getBspOnly() + "," + "turnInPlayEnabled=" + getTurnInPlayEnabled() + "," + "inPlayOnly=" + getInPlayOnly() + "," + "marketBettingTypes="
        + getMarketBettingTypes() + "," + "marketCountries=" + getMarketCountries() + "," + "marketTypeCodes=" + getMarketTypeCodes() + ","
        + "marketStartTime=" + getMarketStartTime() + "," + "withOrders=" + getWithOrders() + "," + "}";
  }

}
