package com.tabtracker.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "histories", uniqueConstraints = @UniqueConstraint(columnNames = { "userId", "songId" }))
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class History extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "userId", referencedColumnName = "id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "songId", referencedColumnName = "id")
  private Song song;

  public History(User user, Song song) {
    this.user = user;
    this.song = song;
  }

  public History(User user, Song song, LocalDateTime updatedAt) {
    this.user = user;
    this.song = song;
    this.updatedAt = updatedAt;
  }
}