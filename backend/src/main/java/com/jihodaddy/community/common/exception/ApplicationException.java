package com.jihodaddy.community.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApplicationException extends RuntimeException{
  
  private final String errorMessage;

}
