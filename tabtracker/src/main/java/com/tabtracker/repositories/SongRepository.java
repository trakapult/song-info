package com.tabtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tabtracker.models.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
  @Query("SELECT s FROM Song s WHERE s.title LIKE %?1% OR s.artist LIKE %?1% OR s.album LIKE %?1% OR s.genre LIKE %?1%")
  List<Song> findAll(String search);
}
