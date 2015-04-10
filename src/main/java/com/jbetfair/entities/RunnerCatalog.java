package com.jbetfair.entities;

import java.util.Map;

public class RunnerCatalog {

  private Long selectionId;
  private String runnerName;
  private Double handicap;
  private int sortPriority;
  private Map<String, String> metadata;

  public Long getSelectionId() {
    return selectionId;
  }

  public void setSelectionId(Long selectionId) {
    this.selectionId = selectionId;
  }

  public String getRunnerName() {
    return runnerName;
  }

  public void setRunnerName(String runnerName) {
    this.runnerName = runnerName;
  }

  public Double getHandicap() {
    return handicap;
  }

  public void setHandicap(Double handicap) {
    this.handicap = handicap;
  }

  public int getSortPriority() {
    return sortPriority;
  }

  public void setSortPriority(int sortPriority) {
    this.sortPriority = sortPriority;
  }

  public Map<String, String> getMetadata() {
    return metadata;
  }

  public void setMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }

  @Override
  public String toString() {
    return "RunnerCatalog [selectionId=" + selectionId + ", runnerName=" + runnerName + ", handicap=" + handicap + ", sortPriority=" + sortPriority + "]";
  }

}
