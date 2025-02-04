package com.jihodaddy.community.util.response;

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

  public static <T> ApiResponse<T> createError(String code, String message) {
    return new ApiResponse<>(code, null, message);
  }

  public static <T> ApiResponse<T> createClientError(String message) {
    return new ApiResponse<>(FAIL_STATUS, null, message);
  }

  public static <T> ApiResponse<T> createServerError(String message) {
    return new ApiResponse<>(FAIL_STATUS, null, message);
  }
}
