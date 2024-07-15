<template>
  <panel>
    <h2 class="text-center mb-4">Bookmarks</h2>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Title</th>
          <th>Artist</th>
          <th>Genre</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(bookmark, index) in bookmarks" :key="index">
          <td>{{ bookmark.song.title }}</td>
          <td>{{ bookmark.song.artist }}</td>
          <td>{{ bookmark.song.genre }}</td>
        </tr>
      </tbody>
    </table>
  </panel>
</template>

<script>
import { mapState } from 'vuex'
import BookmarkService from '@/services/BookmarkService'

export default {
  data () {
    return {
      bookmarks: null
    }
  },
  computed: {
    ...mapState(['isUserLoggedIn', 'user'])
  },
  async mounted () {
    if (this.isUserLoggedIn) {
      this.bookmarks = (await BookmarkService.index()).data
    }
  }
}
</script>
