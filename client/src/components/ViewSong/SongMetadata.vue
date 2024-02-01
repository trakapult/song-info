<template>
  <div class="container">
    <div class="row justify-content-left" style="height: 350px">
      <panel title="Song Metadata">
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
            <button @click="setBookmark"
              class="btn btn-primary"
              style="margin-top: 0.5em;"
              v-if="isUserLoggedIn && !bookmark">
              Bookmark
            </button>
            <button @click="unsetBookmark"
              class="btn btn-primary"
              style="margin-top: 0.5em;"
              v-if="isUserLoggedIn && bookmark">
              Unbookmark
            </button>
          </div>
          <div class="col-md-5">
            <img class="song-album-image" :src="song.albumImageUrl">
            <br>
            <div class="song-album">{{song.album}}</div>
          </div>
        </div>
      </panel>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import BookmarkService from '@/services/BookmarkService'

export default {
  data () {
    return {
      bookmark: null
    }
  },
  props: ['song'],
  computed: {
    ...mapState(['isUserLoggedIn', 'user']) // so that $store.state.isUserLoggedIn can be accessed as isUserLoggedIn
  }, // song ()
  async mounted () {
    if (this.isUserLoggedIn) {
      const bookmarks = (await BookmarkService.index({
        // userId: this.user.id,
        songId: this.song.id
      })).data
      if (bookmarks.length) {
        this.bookmark = bookmarks[0]
      } else {
        this.bookmark = null
      }
    }
  },
  methods: {
    async setBookmark () {
      try {
        this.bookmark = (await BookmarkService.post({
          // userId: this.user.id,
          songId: this.song.id
        })).data
      } catch (err) {
        console.log(err)
      }
    },
    async unsetBookmark () {
      try {
        await BookmarkService.delete(this.bookmark.id)
        this.bookmark = null
      } catch (err) {
        console.log(err)
      }
    }
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
