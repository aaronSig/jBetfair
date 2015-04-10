package com.jbetfair.entities;

import java.util.Date;

public class TimeRange {

  private Date from;
  private Date to;

  public TimeRange() {
  }

  public TimeRange(Date from) {
    this.from = from;
  }

  public TimeRange(Date from, Date to) {
    this.from = from;
    this.to = to;
  }

  public Date getFrom() {
    return from;
  }

  public TimeRange setFrom(Date from) {
    this.from = from;
    return this;
  }

  public Date getTo() {
    return to;
  }

  public TimeRange setTo(Date to) {
    this.to = to;
    return this;
  }

  @Override
  public String toString() {
    return "TimeRange [from=" + from + ", to=" + to + "]";
  }

}
