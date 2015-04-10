package com.jbetfair.entities;

import java.util.Date;

public class Event {

  private String id;
  private String name;
  private String countryCode;
  private String timezone;
  private Date openDate;
  private String venue;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getTimezone() {
    return timezone;
  }

  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  public Date getOpenDate() {
    return openDate;
  }

  public void setOpenDate(Date openDate) {
    this.openDate = openDate;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public String toString() {
    return "{" + "" + "id=" + getId() + "," + "name=" + getName() + "," + "countryCode=" + getCountryCode() + "," + "timezone=" + getTimezone() + ","
        + "openDate=" + getOpenDate() + "," + "}";
  }

}
