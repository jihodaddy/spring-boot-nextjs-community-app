package com.jihodaddy.community.exception;

public abstract class BadRequestException extends ApplicationException {

  protected BadRequestException(String errorMessage) {
    super(errorMessage);
  }
  
}
