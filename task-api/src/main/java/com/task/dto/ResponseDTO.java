package com.task.dto;

import java.util.List;
import java.util.Map;
import org.slf4j.MDC;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class ResponseDTO {

  public ResponseDTO(String status, String message, Map<String, String> errors, List<Object> data) {
    this.status = status;
    this.message = message;
    this.errors = errors;
    this.data = data;
    this.transactionId = MDC.get("transactionId");
  }

  private String transactionId;
  private String status;
  private String message;
  private Map<String, String> errors;
  private List<Object> data;
}
