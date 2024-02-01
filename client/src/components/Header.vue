<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container">
      <a class="navbar-brand" href="/#/">Tab-tracker</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item" v-if="!$store.state.isUserLoggedIn">
            <a class="nav-link" href="/#/login">Login</a>
          </li>
          <li class="nav-item" v-if="!$store.state.isUserLoggedIn">
            <a class="nav-link" href="/#/register">Register</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/#/songs">Songs</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" v-if="$store.state.isUserLoggedIn" @click="logout" href>Logout</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  methods: {
    logout () {
      console.log('logout')
      this.$store.dispatch('setUser', null)
      this.$store.dispatch('setToken', null)
      this.$router.push({name: '/'})
    }
  }
}
</script>
