package com.tabtracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "songs")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Song extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String artist;

  private String genre;

  private String album;

  private String albumImageUrl;

  private String youtubeId;

  @Column(columnDefinition = "TEXT")
  private String lyrics;

  @Column(columnDefinition = "TEXT")
  private String tab;

  public Song(String title, String artist, String genre, String album, String albumImageUrl, String youtubeId,
      String lyrics, String tab) {
    this.title = title;
    this.artist = artist;
    this.genre = genre;
    this.album = album;
    this.albumImageUrl = albumImageUrl;
    this.youtubeId = youtubeId;
    this.lyrics = lyrics;
    this.tab = tab;
  }
}
