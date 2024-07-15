package com.tabtracker.controllers;

import java.util.Map;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tabtracker.services.HistoryService;
import com.tabtracker.models.History;
import com.tabtracker.models.User;
import com.tabtracker.utils.ErrorMessage;

@RestController
@RequestMapping("/histories")
public class HistoryController {
  @Autowired
  private HistoryService historyService;

  @GetMapping("")
  public ResponseEntity<Object> getHistories(HttpServletRequest req) {
    try {
      User user = (User) req.getAttribute("user");
      Long userId = user.getId();
      List<History> res = historyService.getHistories(userId);
      return ResponseEntity.status(HttpStatus.OK).body(res);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
  }

  @PostMapping("")
  public ResponseEntity<Object> createHistory(HttpServletRequest req, @RequestBody Map<String, String> body) {
    try {
      User user = (User) req.getAttribute("user");
      Long userId = user.getId();
      String songIdStr = body.get("songId");
      Long songId = songIdStr == null ? null : Long.parseLong(songIdStr);
      History res = historyService.createHistory(userId, songId);
      return ResponseEntity.status(HttpStatus.CREATED).body(res);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
  }
}
