package com.jihodaddy.community.common.exception;

public record ErrorResponse(
  String errorCode, String message, String description
) {
  
  public static ErrorResponse of(ErrorCode errorCode) {
    return new ErrorResponse(errorCode.name(), errorCode.getMessage(), null);
  }
  
  public static ErrorResponse of(ErrorCode errorCode, String description) {
    return new ErrorResponse(errorCode.name(), errorCode.getMessage(), description);
  }
}
