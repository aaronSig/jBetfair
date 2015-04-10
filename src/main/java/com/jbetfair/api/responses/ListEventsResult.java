package com.jbetfair.api.responses;

import com.jbetfair.entities.Event;

public class ListEventsResult {

  private int marketCount;
  private Event event;

  public int getMarketCount() {
    return marketCount;
  }

  public void setMarketCount(int marketCount) {
    this.marketCount = marketCount;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

}
