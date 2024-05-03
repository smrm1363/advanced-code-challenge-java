package com.statista.code.challenge.infrastructure.in.web;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.MULTI_STATUS;

@ControllerAdvice
@Slf4j
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException ex
  ) {
    Map<String, Object> body = new LinkedHashMap<>();
    LocalDateTime now = now();
    body.put("timestamp", now);
    body.put("message", ex.getMessage());
    log.debug(
        String.format("Timestamp: %s ConstraintViolationException: %s", now, ex.getMessage()));
    return new ResponseEntity<>(body, MULTI_STATUS);
  }

  @ExceptionHandler({NoSuchElementException.class})
  public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    LocalDateTime now = now();
    body.put("timestamp", now);
    body.put("message", ex.getMessage());
    log.debug(String.format("Timestamp: %s NoSuchElementException: %s", now, ex.getMessage()));
    return new ResponseEntity<>(body, MULTI_STATUS);
  }
}
