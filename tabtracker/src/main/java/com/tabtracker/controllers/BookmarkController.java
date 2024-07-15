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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tabtracker.services.BookmarkService;
import com.tabtracker.models.Bookmark;
import com.tabtracker.models.User;
import com.tabtracker.utils.ErrorMessage;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {
  @Autowired
  private BookmarkService bookmarkService;

  @GetMapping("")
  public ResponseEntity<Object> getBookmarks(HttpServletRequest req) {
    try {
      User user = (User) req.getAttribute("user");
      Long userId = user.getId();
      String songIdStr = req.getParameter("songId");
      Long songId = songIdStr == null ? null : Long.parseLong(songIdStr);
      System.out.println("userId: " + userId + ", songId: " + songId);
      List<Bookmark> res = bookmarkService.getBookmarks(userId, songId);
      return ResponseEntity.status(HttpStatus.OK).body(res);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
  }

  @PostMapping("")
  public ResponseEntity<Object> createBookmark(HttpServletRequest req, @RequestBody Map<String, String> body) {
    try {
      User user = (User) req.getAttribute("user");
      Long userId = user.getId();
      String songIdStr = body.get("songId");
      Long songId = songIdStr == null ? null : Long.parseLong(songIdStr);
      Bookmark res = bookmarkService.createBookmark(userId, songId);
      return ResponseEntity.status(HttpStatus.CREATED).body(res);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteBookmark(@PathVariable Long id) {
    try {
      bookmarkService.deleteBookmark(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
  }
}
