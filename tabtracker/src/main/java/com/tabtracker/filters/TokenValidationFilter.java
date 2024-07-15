package com.tabtracker.filters;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.tabtracker.services.JwtService;
import com.tabtracker.models.User;

public class TokenValidationFilter extends OncePerRequestFilter {
  private JwtService jwtService;

  public TokenValidationFilter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws ServletException, IOException {
    if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
      chain.doFilter(req, res);
      return;
    }
    String header = req.getHeader("Authorization");
    if (header == null || !header.startsWith("Bearer ")) {
      res.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
      return;
    }
    String token = header.substring(7);
    System.out.println("Token: " + token);
    User user = jwtService.validateToken(token);
    if (user == null) {
      res.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
      return;
    }
    req.setAttribute("user", user);
    chain.doFilter(req, res);
  }
}