package com.task.config;

import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class TransactionIdFilter implements Filter {

  private static final String TRANSACTION_ID_KEY = "transactionId";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    try {
      String transactionId = UUID.randomUUID().toString();
      MDC.put(TRANSACTION_ID_KEY, transactionId);
      HttpServletResponse httpServletResponse = (HttpServletResponse) response;
      httpServletResponse.setHeader("X-Transaction-Id", transactionId);

      chain.doFilter(request, response);
    } finally {
      MDC.remove(TRANSACTION_ID_KEY);
    }
  }

  @Override
  public void init(FilterConfig filterConfig) {}

  @Override
  public void destroy() {}
}

