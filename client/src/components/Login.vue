<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <panel title="Login">
          <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input id="email" v-model="email" class="form-control" required>
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input id="password" type="password" v-model="password" class="form-control" required>
          </div>
          <div class="error" v-html="error"></div>
          <div class="d-grid">
            <button type="submit" class="btn btn-primary" @click="login">Login</button>
          </div>
        </panel>
      </div>
    </div>
  </div>
</template>

<script>
import AuthenticationService from '@/services/AuthenticationService'

export default {
  data () {
    return {
      email: 'abc@123.com',
      password: '12345678',
      error: null
    }
  },
  methods: {
    async login () {
      try {
        const response = await AuthenticationService.login({
          email: this.email,
          password: this.password
        })
        console.log(response.data)
        this.$store.dispatch('setToken', response.data.token)
        this.$store.dispatch('setUser', response.data.user)
        this.$router.push({name: 'songs'})
      } catch (error) {
        this.error = error.response.data.error
      }
    }
  }
}
</script>

<style scoped>
.error {
  color: red;
}
</style>
