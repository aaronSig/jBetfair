package com.jbetfair.exceptions;


public class BetfairException extends Exception {
  private static final long serialVersionUID = 62956855946788584L;

  private String errorDetails;
  private String errorCode;
  private String requestUUID;

  public BetfairException() {
    super();
  }

  public BetfairException(String message, Throwable cause) {
    super(message, cause);
    this.errorDetails = message;
  }

  public BetfairException(String errorDetails, String errorCode, String requestUUID) {
    super(errorCode + ": " + errorDetails);
    this.errorCode = errorCode;
    this.errorDetails = errorDetails;
    this.requestUUID = requestUUID;
  }

  @Override
  public String getMessage() {
    StringBuffer buffer = new StringBuffer();
    if (super.getMessage() != null) {
      buffer.append(super.getMessage());
      buffer.append(" ");
    }
    if (errorDetails != null) {
      buffer.append(errorDetails);
      buffer.append(" ");
    }
    if (errorCode != null) {
      buffer.append(errorCode);
    }
    return super.getMessage() + " " + errorDetails + " " + errorCode;
  }

  public String getErrorDetails() {
    return errorDetails;
  }

  public void setErrorDetails(String errorDetails) {
    this.errorDetails = errorDetails;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getRequestUUID() {
    return requestUUID;
  }

  public void setRequestUUID(String requestUUID) {
    this.requestUUID = requestUUID;
  }

  @Override
  public String toString() {
    return "ErrorCode: " + errorCode + " ErrorDetails: " + errorDetails + " RequestUUID: " + requestUUID;
  }

}
