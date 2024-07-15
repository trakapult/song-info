package com.tabtracker.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.tabtracker.models.User;
import com.tabtracker.repositories.UserRepository;

@Service
public class JwtService {
  private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private static final long ONE_WEEK = 7 * 24 * 60 * 60 * 1000;

  @Autowired
  private UserRepository userRepository;

  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", user.getId());
    claims.put("email", user.getEmail());
    return createToken(claims);
  }

  public String createToken(Map<String, Object> claims) {
    String token = Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + ONE_WEEK))
        .signWith(SECRET_KEY).compact();
    System.out.println("Token: " + token);
    Claims extractedClaims = extractAllClaims(token);
    System.out.println("Claims: " + extractedClaims);
    return token;
  }

  private Claims extractAllClaims(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(SECRET_KEY)
        .build()
        .parseClaimsJws(token)
        .getBody();
    return claims;
  }

  public Long extractId(String token) {
    return extractAllClaims(token).get("id", Long.class);
  }

  private Date extractExpiration(String token) {
    return extractAllClaims(token).getExpiration();
  }

  public User validateToken(String token) {
    if (token == null) {
      return null;
    }
    Long id = extractId(token);
    System.out.println("ID: " + id);
    User user = userRepository.findById(id).orElse(null);
    if (user == null || extractExpiration(token).before(new Date())) {
      return null;
    }
    return user;
  }

  public User getUser() {
    return userRepository.findByEmail("abc@123.com").orElse(null);
  }
}
