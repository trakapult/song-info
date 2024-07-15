package com.tabtracker.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabtracker.models.History;
import com.tabtracker.repositories.HistoryRepository;
import com.tabtracker.models.User;
import com.tabtracker.repositories.UserRepository;
import com.tabtracker.models.Song;
import com.tabtracker.repositories.SongRepository;

@Service
public class HistoryService {
  @Autowired
  private HistoryRepository historyRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SongRepository songRepository;

  public void deleteHistories() {
    historyRepository.deleteAll();
    System.out.println("Histories Deleted!");
  }

  public List<History> getHistories(Long userId) {
    return historyRepository.findByUserIdOrderByUpdatedAtDesc(userId);
  }

  public History createHistory(Long userId, Long songId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    Song song = songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));
    History history = historyRepository.findByUserIdAndSongId(userId, songId);
    if (history != null) {
      history.setUpdatedAt(LocalDateTime.now());
      historyRepository.save(history);
      return history;
    }
    return historyRepository.save(new History(user, song));
  }
}
