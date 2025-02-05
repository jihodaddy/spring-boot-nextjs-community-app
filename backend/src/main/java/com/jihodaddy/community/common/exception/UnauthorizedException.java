package com.jihodaddy.community.common.exception;

public abstract class UnauthorizedException extends ApplicationException{

  protected UnauthorizedException(String errorMessage) {
    super(errorMessage);
  }
  
}
