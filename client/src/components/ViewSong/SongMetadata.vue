<template>
  <div class="container">
    <div class="row justify-content-left" style="height: 350px">
      <Panel title="Song Metadata">
        <div class="song row justify-content-center">
          <div class="col-md-5">
            <div class="song-title">{{song.title}}</div>
            <div class="song-artist">{{song.artist}}</div>
            <div class="song-genre">{{song.genre}}</div>
            <router-link :to="{name: 'song-edit', params: {songId: song.id}}"
              class="btn btn-primary"
              style="margin-top: 0.5em;">
              Edit
            </router-link>
          </div>
          <div class="col-md-5">
            <img class="song-album-image" :src="song.albumImageUrl">
            <br>
            <div class="song-album">{{song.album}}</div>
          </div>
        </div>
      </Panel>
    </div>
  </div>
</template>

<script>
import Panel from '@/components/Panel'

export default {
  props: ['song'],
  components: {
    Panel
  }
}
</script>

<style scoped>
.song {
  margin: 1.5em 0;
}
.song-title {
  font-size: 1.7em;
  font-weight: bold;
}
.song-artist {
  font-size: 1.6em;
}
.song-genre {
  font-size: 1.5em;
}
.song-album-image {
  width: 90%;
}
.song-album {
  font-size: 1em;
}
</style>
