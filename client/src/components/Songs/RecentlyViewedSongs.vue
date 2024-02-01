<template>
  <panel>
    <h2 class="text-center mb-4">Recently Viewed Songs</h2>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Title</th>
          <th>Artist</th>
          <th>Genre</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(history, index) in histories" :key="index">
          <td>{{ history.Song.title }}</td>
          <td>{{ history.Song.artist }}</td>
          <td>{{ history.Song.genre }}</td>
        </tr>
      </tbody>
    </table>
  </panel>
</template>

<script>
import { mapState } from 'vuex'
import SongHistoryService from '@/services/SongHistoryService'

export default {
  data () {
    return {
      histories: null
    }
  },
  computed: {
    ...mapState(['isUserLoggedIn', 'user'])
  },
  async mounted () {
    if (this.isUserLoggedIn) {
      this.histories = (await SongHistoryService.index()).data
    }
  }
}
</script>
