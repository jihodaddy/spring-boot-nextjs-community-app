package com.jihodaddy.community.common.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

  private static final String SUCCESS_STATUS = "success";
  private static final String FAIL_STATUS = "fail";
  private static final String ERROR_STATUS = "error";

  private String status;
  private T data;
  private String message;
  
  public ApiResponse(String status, T data, String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }

  public static <T> ApiResponse<T> createSuccess(T data, String message) {
    return new ApiResponse<>(SUCCESS_STATUS, data, message);
  }

  public static <T> ApiResponse<T> createSuccssNoContent(String message){
    return new ApiResponse<>(SUCCESS_STATUS, null, message);
  }

}
