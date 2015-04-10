package com.jbetfair.exceptions;

public class Data {

  private String exceptionName;
  private BetfairException APINGException;

  public String getExceptionName() {
    return exceptionName;
  }

  public void setExceptionName(String exceptionName) {
    this.exceptionName = exceptionName;
  }

  public BetfairException getBetfairException() {
    return APINGException;
  }

  public void setAPINGException(BetfairException betfairException) {
    this.APINGException = betfairException;
  }

}
