package com.tabtracker.services;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tabtracker.models.User;
import com.tabtracker.repositories.UserRepository;

@Service
public class UserService {
  private UserRepository userRepository;
  private BCryptPasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public void seedUsers() {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {
    };
    InputStream inputStream = TypeReference.class.getResourceAsStream("/seeds/users.json");
    try {
      List<User> users = mapper.readValue(inputStream, typeReference);
      for (User user : users) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
      }
      userRepository.saveAll(users);
      System.out.println("Users Saved!");
    } catch (IOException e) {
      System.out.println("Unable to save users: " + e.getMessage());
    }
  }

  public void deleteUsers() {
    userRepository.deleteAll();
    System.out.println("Users Deleted!");
  }

  public User register(String email, String password) {
    if (password.length() < 6) {
      throw new IllegalArgumentException("密码长度至少为6位");
    }
    try {
      String encodedPassword = passwordEncoder.encode(password);
      User user = new User(email, encodedPassword);
      userRepository.save(user);
      return user;
    } catch (DataIntegrityViolationException e) {
      throw new IllegalArgumentException("用户名或邮箱已存在");
    }
  }

  public User login(String email, String password) {
    Optional<User> res = userRepository.findByEmail(email);
    if (res.isEmpty() || !passwordEncoder.matches(password, res.get().getPassword())) {
      throw new IllegalArgumentException("邮箱或密码错误");
    }
    return res.get();
  }
}