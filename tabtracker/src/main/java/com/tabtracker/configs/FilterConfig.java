package com.tabtracker.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import com.tabtracker.filters.TokenValidationFilter;
import com.tabtracker.services.JwtService;

@Configuration
public class FilterConfig {
  @Autowired
  private JwtService jwtService;

  @Bean
  public FilterRegistrationBean<TokenValidationFilter> tokenValidationBean() {
    FilterRegistrationBean<TokenValidationFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new TokenValidationFilter(jwtService));
    registrationBean.addUrlPatterns("/bookmarks/*");
    registrationBean.addUrlPatterns("/histories/*");
    registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
    return registrationBean;
  }
}
