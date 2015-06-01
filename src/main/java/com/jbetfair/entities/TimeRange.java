package com.jbetfair.entities;

import java.util.Date;

import org.joda.time.DateTime;

public class TimeRange {

  private Date from;
  private Date to;

  public static TimeRange fromNow() {
    return new TimeRange(new Date());
  }

  public TimeRange() {
  }

  public TimeRange(Date from) {
    this.from = from;
  }

  public TimeRange(Date from, Date to) {
    this.from = from;
    this.to = to;
  }

  public TimeRange(DateTime from) {
    this.from = from.toDate();
  }

  public TimeRange(DateTime from, DateTime to) {
    this.from = from.toDate();
    this.to = to.toDate();
  }

  public TimeRange(Date from, DateTime to) {
    this.from = from;
    this.to = to.toDate();
  }

  public TimeRange(DateTime from, Date to) {
    this.from = from.toDate();
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

  /***
   * Sets the time range from Date to 23:59 tonight
   * 
   * @return
   */
  public TimeRange toMidnight() {
    this.to = new DateTime().withTime(23, 59, 59, 99).toDate();
    return this;
  }

  @Override
  public String toString() {
    return "TimeRange [from=" + from + ", to=" + to + "]";
  }

}
