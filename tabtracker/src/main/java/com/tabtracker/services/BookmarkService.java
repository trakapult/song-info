package com.tabtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabtracker.models.Bookmark;
import com.tabtracker.repositories.BookmarkRepository;
import com.tabtracker.models.User;
import com.tabtracker.repositories.UserRepository;
import com.tabtracker.models.Song;
import com.tabtracker.repositories.SongRepository;

@Service
public class BookmarkService {
  @Autowired
  private BookmarkRepository bookmarkRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SongRepository songRepository;

  public void deleteBookmarks() {
    try {
      bookmarkRepository.deleteAll();
      System.out.println("Bookmarks Deleted!");
    } catch (Exception e) {
      System.out.println("Unable to delete bookmarks: " + e.getMessage());
    }
  }

  public List<Bookmark> getBookmarks(Long userId, Long songId) {
    if (songId == null) {
      return bookmarkRepository.findByUserId(userId);
    }
    return bookmarkRepository.findByUserIdAndSongId(userId, songId);
  }

  public Bookmark createBookmark(Long userId, Long songId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    Song song = songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));
    return bookmarkRepository.save(new Bookmark(user, song));
  }

  public void deleteBookmark(Long id) {
    bookmarkRepository.deleteById(id);
  }
}
