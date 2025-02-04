package com.jihodaddy.community;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
  
  @GetMapping("/")
  public ResponseEntity<Object> test() {
      return new ResponseEntity<>("통신성공", HttpStatus.OK);
  }
  
}
