package com.jbetfair.api.responses;

import com.jbetfair.entities.TimeRange;

public class ListTimeRangesResult {
  private TimeRange timeRange;
  private int marketCount;

  public TimeRange getTimeRange() {
    return timeRange;
  }

  public void setTimeRange(TimeRange timeRange) {
    this.timeRange = timeRange;
  }

  public int getMarketCount() {
    return marketCount;
  }

  public void setMarketCount(int marketCount) {
    this.marketCount = marketCount;
  }

  @Override
  public String toString() {
    return "ListTimeRangesResult [timeRange=" + timeRange + ", marketCount=" + marketCount + "]";
  }

}
