package com.jbetfair.api;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.jbetfair.api.entities.MarketBook;
import com.jbetfair.api.entities.MarketCatalogue;
import com.jbetfair.api.responses.EventTypeResult;
import com.jbetfair.api.responses.GenericAPIResponse;
import com.jbetfair.api.responses.ListCompetitionResult;
import com.jbetfair.api.responses.ListCountriesResult;
import com.jbetfair.api.responses.ListEventsResult;
import com.jbetfair.api.responses.ListMarketTypesResult;
import com.jbetfair.api.responses.ListTimeRangesResult;
import com.jbetfair.api.responses.ListVenuesResult;
import com.jbetfair.api.responses.PlaceOrdersResult;

public enum ApiOperation {

  LIST_EVENT_TYPES("listEventTypes", new TypeToken<GenericAPIResponse<EventTypeResult>>() {}.getType()),
  LIST_COMPETITIONS("listCompetitions", new TypeToken<GenericAPIResponse<ListCompetitionResult>>() {}.getType()),
  LIST_TIME_RANGES("listTimeRanges", new TypeToken<GenericAPIResponse<ListTimeRangesResult>>() {}.getType()),
  LIST_EVENTS("listEvents", new TypeToken<GenericAPIResponse<ListEventsResult>>() {}.getType()),
  LIST_MARKET_TYPES("listMarketTypes", new TypeToken<GenericAPIResponse<ListMarketTypesResult>>() {}.getType()),
  LIST_COUNTRIES("listCountries", new TypeToken<GenericAPIResponse<ListCountriesResult>>() {}.getType()),
  LIST_VENUES("listVenues", new TypeToken<GenericAPIResponse<ListVenuesResult>>() {}.getType()),
  LIST_MARKET_CATALOGUE("listMarketCatalogue", new TypeToken<GenericAPIResponse<MarketCatalogue>>() {}.getType()),
  LIST_MARKET_BOOK("listMarketBook", new TypeToken<GenericAPIResponse<MarketBook>>() {}.getType()),
  PLACE_ORDERS("placeOrders", new TypeToken<GenericAPIResponse<PlaceOrdersResult>>() {}.getType());

  private final String text;
  private final Type type;

  private ApiOperation(String text, Type type) {
    this.text = text;
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  @Override
  public String toString() {
    return text;
  }

}
