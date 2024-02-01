<template>
  <div class="container">
    <div class="row justify-content-left">
      <div class="col-md-4">
        <panel title="Create Song">
          <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input id="title" v-model="song.title" class="form-control" required>
          </div>
          <div class="mb-3">
            <label for="artist" class="form-label">Artist</label>
            <input id="artist" v-model="song.artist" class="form-control" required>
          </div>
          <div class="mb-3">
            <label for="genre" class="form-label">Genre</label>
            <input id="genre" v-model="song.genre" class="form-control" required>
          </div>
          <div class="mb-3">
            <label for="album" class="form-label">Album</label>
            <input id="album" v-model="song.album" class="form-control" required>
          </div>
          <div class="mb-3">
            <label for="albumImageUrl" class="form-label">Album Image URL</label>
            <textarea id="albumImageUrl" v-model="song.albumImageUrl" class="form-control" required></textarea>
          </div>
          <div class="mb-3">
            <label for="youtubeId" class="form-label">Youtube ID</label>
            <textarea id="youtubeId" v-model="song.youtubeId" class="form-control" required></textarea>
          </div>
        </panel>
      </div>
      <div class="col-md-8">
        <panel title="Song Structure">
          <div class="mb-3">
            <label for="lyrics" class="form-label">Lyrics</label>
            <textarea id="lyrics" v-model="song.lyrics" class="form-control" required></textarea>
          </div>
          <div class="mb-3">
            <label for="tab" class="form-label">Tab</label>
            <textarea id="tab" v-model="song.tab" class="form-control" required></textarea>
          </div>
        </panel>
        <div class="alert alert-danger" v-if="error" style="margin-top: 20px;">{{error}}</div>
        <div class="d-grid" style="margin-top: 20px;">
          <button type="submit" class="btn btn-primary" @click="create">Create Song</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SongService from '@/services/SongService'

export default {
  data () {
    return {
      song: {
        title: null,
        artist: null,
        genre: null,
        album: null,
        albumImageUrl: null,
        youtubeId: null,
        lyrics: null,
        tab: null
      },
      error: null
    }
  },
  methods: {
    async create () {
      this.error = null
      const areAllFieldsFilled = Object.keys(this.song).every(key => this.song[key])
      if (!areAllFieldsFilled) {
        this.error = 'All fields are required.'
        return
      }
      try {
        await SongService.post(this.song)
        this.$router.push({name: 'songs'})
      } catch (err) {
        console.log(err)
      }
    }
  }
}
</script>
