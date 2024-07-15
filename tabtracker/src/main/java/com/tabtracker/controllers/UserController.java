package com.tabtracker.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.Data;

import com.tabtracker.models.User;
import com.tabtracker.services.UserService;
import com.tabtracker.services.JwtService;
import com.tabtracker.utils.ErrorMessage;

@RestController
@RequestMapping("")
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private JwtService jwtService;

  @Data
  class UserState {
    User user;
    String token;

    public UserState(User user) {
      this.user = user;
      this.token = jwtService.generateToken(user);
    }
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody User user) {
    try {
      User res = userService.register(user.getEmail(), user.getPassword());
      UserState state = new UserState(res);
      return ResponseEntity.status(HttpStatus.CREATED).body(state);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody User user) {
    try {
      User res = userService.login(user.getEmail(), user.getPassword());
      UserState state = new UserState(res);
      return ResponseEntity.status(HttpStatus.CREATED).body(state);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
  }
}