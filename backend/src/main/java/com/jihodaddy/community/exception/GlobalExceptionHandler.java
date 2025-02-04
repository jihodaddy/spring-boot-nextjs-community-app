package com.jihodaddy.community.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    log.error(ex.getMessage(), ex);
    List<String> errorMessage = ex.getBindingResult().getFieldErrors().stream()
      .map(e -> e.getDefaultMessage())
      .toList();

    return ResponseEntity.status(status.value()).body(ErrorResponse.of(ErrorCode.METHOD_ARGUMENT_NOT_VALID, errorMessage.toString()));
  }

  @Override
  protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    log.error(ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.of(ErrorCode.NO_RESOURCE_FOUND));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Exception ex) {
    log.error(ex.getMessage(), ex);
    return ResponseEntity.internalServerError().body(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage()));
  }
  
}
