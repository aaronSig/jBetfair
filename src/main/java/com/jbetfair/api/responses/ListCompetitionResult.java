package com.jbetfair.api.responses;

import com.jbetfair.entities.Competition;

public class ListCompetitionResult {

  private Competition competition;
  private int marketCount;
  private String competitionRegion;

  public Competition getCompetition() {
    return competition;
  }

  public void setCompetition(Competition competition) {
    this.competition = competition;
  }

  public int getMarketCount() {
    return marketCount;
  }

  public void setMarketCount(int marketCount) {
    this.marketCount = marketCount;
  }

  public String getCompetitionRegion() {
    return competitionRegion;
  }

  public void setCompetitionRegion(String competitionRegion) {
    this.competitionRegion = competitionRegion;
  }

  @Override
  public String toString() {
    return "ListCompetitionResult [competition=" + competition + ", marketCount=" + marketCount + ", competitionRegion=" + competitionRegion + "]";
  }

}
