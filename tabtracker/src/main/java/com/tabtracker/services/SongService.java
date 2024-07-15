package com.tabtracker.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabtracker.models.Song;
import com.tabtracker.repositories.SongRepository;

@Service
public class SongService {
  @Autowired
  private SongRepository songRepository;

  public void seedSongs() {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<Song>> typeReference = new TypeReference<List<Song>>() {
    };
    InputStream inputStream = TypeReference.class.getResourceAsStream("/seeds/songs.json");
    try {
      List<Song> songs = mapper.readValue(inputStream, typeReference);
      for (Song song : songs) {
        System.out.println(song.getTitle() + song.getArtist() + song.getGenre() + song.getAlbum());
        System.out.println(song.getLyrics());
      }
      songRepository.saveAll(songs);
      System.out.println("Songs Saved!");
    } catch (IOException e) {
      System.out.println("Unable to save songs: " + e.getMessage());
    }
  }

  public void deleteSongs() {
    songRepository.deleteAll();
    System.out.println("Songs Deleted!");
  }

  public List<Song> getSongs(String search) {
    return songRepository.findAll(search == null ? "" : search);
  }

  public Song getSong(Long id) {
    return songRepository.findById(id).orElseThrow(() -> new RuntimeException("Song not found"));
  }

  public Song createSong(Song song) {
    return songRepository.save(song);
  }

  public void updateSong(Long id, Song song) {
    songRepository.save(song);
  }
}
