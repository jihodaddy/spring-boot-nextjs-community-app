package com.jihodaddy.community.common.exception;

public abstract class BadRequestException extends ApplicationException {

  protected BadRequestException(String errorMessage) {
    super(errorMessage);
  }
  
}
