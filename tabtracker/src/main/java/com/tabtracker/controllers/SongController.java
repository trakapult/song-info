package com.tabtracker.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tabtracker.services.SongService;
import com.tabtracker.models.Song;
import com.tabtracker.utils.ErrorMessage;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/songs")
public class SongController {
  @Autowired
  private SongService songService;

  @GetMapping("")
  public ResponseEntity<Object> getSongs(String search) {
    try {
      List<Song> res = songService.getSongs(search);
      return ResponseEntity.status(HttpStatus.OK).body(res);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getSong(@PathVariable Long id) {
    try {
      Song res = songService.getSong(id);
      System.out.println("res: " + res.getTitle());
      return ResponseEntity.status(HttpStatus.OK).body(res);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
  }

  @PostMapping("")
  public ResponseEntity<Object> createSong(@RequestBody Song song) {
    try {
      Song res = songService.createSong(song);
      return ResponseEntity.status(HttpStatus.CREATED).body(res);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateSong(@PathVariable Long id, @RequestBody Song song) {
    try {
      songService.updateSong(id, song);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage(e.getMessage()));
    }
  }
}
