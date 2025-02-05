package com.jihodaddy.community.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class ApplicationException extends RuntimeException{
  
  private final String errorMessage;

}
