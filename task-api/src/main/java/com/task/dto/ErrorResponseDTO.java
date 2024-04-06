package com.task.dto;

import java.time.LocalDateTime;
import org.slf4j.MDC;
import lombok.Data;

@Data
public class ErrorResponseDTO {

  public ErrorResponseDTO(int status, String message, String apiPath, LocalDateTime timestamp) {
    this.status = status;
    this.message = message;
    this.apiPath = apiPath;
    this.timestamp = timestamp;
    this.transactionId = MDC.get("transactionId");
  }

  private String transactionId;
  private final int status;
  private final String message;
  private final String apiPath;
  private final LocalDateTime timestamp;
}
