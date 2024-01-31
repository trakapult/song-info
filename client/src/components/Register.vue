<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <Panel title="Register">
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
            <button type="submit" class="btn btn-primary" @click="login">Register</button>
          </div>
        </Panel>
      </div>
    </div>
  </div>
</template>

<script>
import AuthenticationService from '@/services/AuthenticationService'
import Panel from '@/components/Panel.vue'

export default {
  data () {
    return {
      email: 'abc@123.com',
      password: '12345678',
      error: null
    }
  },
  methods: {
    async register () {
      try {
        const response = await AuthenticationService.register({
          email: this.email,
          password: this.password
        })
        console.log(response.data)
        this.$store.dispatch('setToken', response.data.token)
        this.$store.dispatch('setUser', response.data.user)
      } catch (error) {
        console.log(error)
        this.error = error.response.data.error
      }
    }
  },
  components: {
    Panel
  }
}
</script>

<style scoped>
.error {
  color: red;
}
</style>
