package com.tabtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tabtracker.models.Bookmark;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
  List<Bookmark> findByUserId(Long userId);

  List<Bookmark> findByUserIdAndSongId(Long userId, Long songId);
}
