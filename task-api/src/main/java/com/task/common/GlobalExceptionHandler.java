package com.task.common;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.task.dto.ErrorResponseDTO;
import com.task.dto.ResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseDTO> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    return new ResponseEntity<>(
        new ResponseDTO(Constants.ERROR_STATUS, Constants.VALIDATION_ERROR_MESSAGE, errors, null),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceAlreadyExistException.class)
  public ResponseEntity<ErrorResponseDTO> handleCustomExceptions(Exception exception,
      WebRequest webRequest) {
    HttpStatus status = HttpStatus.CONFLICT;
    return buildResponseEntity(status, exception.getMessage(), webRequest.getDescription(false));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception exception,
      WebRequest webRequest) {
    return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(),
        webRequest.getDescription(false));
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponseDTO> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex, WebRequest webRequest) {
    return buildResponseEntity(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(),
        webRequest.getDescription(false));
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ErrorResponseDTO> handleHttpMediaTypeNotSupportedException(
      HttpMediaTypeNotSupportedException ex, WebRequest webRequest) {
    return buildResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage(),
        webRequest.getDescription(false));
  }

  @ExceptionHandler({MissingServletRequestParameterException.class,
      ServletRequestBindingException.class})
  public ResponseEntity<ErrorResponseDTO> handleRequestParameterBindingException(Exception ex,
      WebRequest webRequest) {
    return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(),
        webRequest.getDescription(false));
  }

  private ResponseEntity<ErrorResponseDTO> buildResponseEntity(HttpStatus status, String message,
      String apiPath) {
    ErrorResponseDTO errorResponse =
        new ErrorResponseDTO(status.value(), message, apiPath, LocalDateTime.now());
    return new ResponseEntity<ErrorResponseDTO>(errorResponse, status);
  }

}
