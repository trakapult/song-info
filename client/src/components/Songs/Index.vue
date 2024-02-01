<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6" v-if="isUserLoggedIn">
        <RecentlyViewedSongs />
        <SongsBookmarks class="mt-2" />
      </div>
      <div :class="isUserLoggedIn ? 'col-md-6' : 'col-md-12'">
        <SongsSearchpanel />
        <Songspanel class="mt-2"/>
      </div>
    </div>
  </div>
</template>

<script>
import SongServive from '@/services/SongService'
import Songspanel from './Songspanel'
import SongsSearchpanel from './SongsSearchpanel'
import SongsBookmarks from './SongsBookmarks'
import RecentlyViewedSongs from './RecentlyViewedSongs'
import { mapState } from 'vuex'

export default {
  components: {
    Songspanel,
    SongsSearchpanel,
    SongsBookmarks,
    RecentlyViewedSongs
  },
  computed: {
    ...mapState(['isUserLoggedIn', 'user'])
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
