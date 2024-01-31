<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <Panel title="Songs">
          <span slot="button">
            <router-link to="/songs/create" class="btn btn-primary">+</router-link>
          </span>
          <div v-for="song in songs" :key="song.title">
            <div class="song row justify-content-center">
                <div class="col-md-3">
                  <div class="song-title">{{song.title}}</div>
                  <div class="song-artist">{{song.artist}}</div>
                  <div class="song-genre">{{song.genre}}</div>
                  <router-link :to="{name: 'song', params: {songId: song.id}}"
                    class="btn btn-primary"
                    style="margin-top: 0.5em;">
                    View
                  </router-link>
                </div>
                <div class="col-md-3">
                  <img class="song-album-image" :src="song.albumImageUrl">
                </div>
            </div>
          </div>
        </Panel>
      </div>
    </div>
  </div>
</template>

<script>
import SongServive from '@/services/SongService'
import Panel from '@/components/Panel'

export default {
  components: {
    Panel
  },
  data () {
    return {
      songs: null
    }
  },
  async mounted () {
    this.songs = (await SongServive.index()).data
  }
}
</script>

<style scoped>
.song {
  margin: 1.5em 0;
}
.song-title {
  font-size: 1.2em;
  font-weight: bold;
}
.song-artist {
  font-size: 1.1em;
}
.song-genre {
  font-size: 1em;
}
.song-album-image {
  width: 120%;
}
</style>
