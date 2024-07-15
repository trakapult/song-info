package com.tabtracker.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tabtracker.models.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
  List<History> findByUserIdOrderByUpdatedAtDesc(Long userId);

  History findByUserIdAndSongId(Long userId, Long songId);
}
