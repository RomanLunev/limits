package org.example.controller;

import org.example.dto.ResponseDto;
import org.example.service.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LimitControllerAdvice {

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseDto illegalArgumentException(IllegalArgumentException e) {
    return new ResponseDto(ResultCode.FAILURE, e.getMessage());
  }
}
