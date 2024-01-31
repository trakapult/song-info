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
import Panel from '@/components/Panel'
import SongMetadata from './SongMetadata'
import YouTube from './YouTube'
import Tab from './Tab'
import Lyrics from './Lyrics'

export default {
  components: {
    Panel,
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
  async mounted () {
    try {
      const songId = this.$store.state.route.params.songId
      this.song = (await SongService.show(songId)).data
    } catch (err) {
      console.log(err)
    }
  }
}
</script>
