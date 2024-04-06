package com.task.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

  @Bean
  FilterRegistrationBean<TransactionIdFilter> transactionIdFilter() {
    FilterRegistrationBean<TransactionIdFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new TransactionIdFilter());
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }
}
