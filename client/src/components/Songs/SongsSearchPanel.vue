<template>
  <panel title="Search...">
    <div class="mb-3">
      <label for="search" class="form-label">...by song title, album, or genre</label>
      <input id="search" v-model="search" class="form-control" required>
    </div>
  </panel>
</template>

<script>
import _ from 'lodash'

export default {
  data () {
    return {
      search: ''
    }
  },
  watch: {
    search: _.debounce(async function (value) {
      const route = {name: 'songs'}
      if (value !== '') {
        route.query = {search: value}
      }
      this.$router.push(route)
    }, 700),
    '$route.query.search': {
      handler (value) {
        this.search = value
      },
      immediate: true
    }
  }
}
</script>
