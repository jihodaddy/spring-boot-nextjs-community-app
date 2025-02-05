package com.jihodaddy.community.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
  
   /* Authentication */
  PASSWORD_INVALID("비밀번호가 일치하지 않습니다."),
  TOKEN_NOT_FOUND("토큰이 존재하지 않습니다."),
  TOKEN_INVALID("유효하지 않은 토큰입니다."),
  FORBIDDEN("접근할 수 없는 행사입니다."),
   /* Request Validation */
  REQUEST_EMPTY("입력 값은 공백일 수 없습니다.")
  /* System */,
  BAD_REQUEST("잘못된 요청입니다."),
  METHOD_ARGUMENT_NOT_VALID("유효성 검사 실패 오류가 발생했습니다."),
  NO_RESOURCE_FOUND("리소스가 존재하지 않습니다."),
  
  NO_RESOURCE_REQUEST("존재하지 않는 자원입니다."),
  MESSAGE_NOT_READABLE("읽을 수 없는 요청입니다."),
  REQUEST_METHOD_NOT_SUPPORTED("지원하지 않는 요청 메서드입니다."),
  INTERNAL_SERVER_ERROR("서버 내부에서 에러가 발생했습니다."),
  ;

  private final String message;

  ErrorCode(String message) {
    this.message = message;
  }
}
