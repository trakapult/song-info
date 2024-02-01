<template>
  <div class="container">
    <div class="row justify-content-left">
      <div class="col-md-6">
        <SongMetadata :song="song" />
      </div>
      <div class="col-md-6">
        <YouTube :youtubeId="song.youtubeId" />
      </div>
    </div>
    <div class="row justify-content-left mt-2">
      <div class="col-md-6">
        <Tab :tab="song.tab" />
      </div>
      <div class="col-md-6">
        <Lyrics :lyrics="song.lyrics" />
      </div>
    </div>
  </div>
</template>

<script>
import SongService from '@/services/SongService'
import SongMetadata from './SongMetadata'
import YouTube from './YouTube'
import Tab from './Tab'
import Lyrics from './Lyrics'
import SongHistoryService from '../../services/SongHistoryService'
import { mapState } from 'vuex'

export default {
  components: {
    SongMetadata,
    YouTube,
    Tab,
    Lyrics
  },
  data () {
    return {
      song: null
    }
  },
  computed: {
    ...mapState(['isUserLoggedIn', 'user', 'route'])
  },
  async mounted () {
    try {
      const songId = this.route.params.songId
      this.song = (await SongService.show(songId)).data
      if (this.isUserLoggedIn) {
        SongHistoryService.post({
          songId: songId
        })
      }
    } catch (err) {
      console.log(err)
    }
  }
}
</script>
