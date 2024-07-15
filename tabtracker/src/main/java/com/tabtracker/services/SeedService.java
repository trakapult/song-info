package com.tabtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeedService {
  @Autowired
  private UserService userService;
  @Autowired
  private SongService songService;
  @Autowired
  private BookmarkService bookmarkService;
  @Autowired
  private HistoryService historyService;

  public void run() {
    bookmarkService.deleteBookmarks();
    historyService.deleteHistories();
    userService.deleteUsers();
    songService.deleteSongs();
    userService.seedUsers();
    songService.seedSongs();
  }
}
