package com.jihodaddy.community.exception;

public abstract class UnauthorizedException extends ApplicationException{

  protected UnauthorizedException(String errorMessage) {
    super(errorMessage);
  }
  
}
