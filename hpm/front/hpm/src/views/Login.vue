<template>
        <div class="row justify-content-center">
            <div class="col-lg-5 col-md-7">
                <div class="card border-0">
                    <div class="card-body px-lg-5 py-lg-5">
                        <div class="text-center text-muted mb-4">
                            <small>HPM</small>
                        </div>
                        <form role="form">
                            <base-input class="input-group-alternative mb-3"
                                        placeholder="ID"
                                        addon-left-icon="ni ni-email-83"
                                        v-model="model.username">
                            </base-input>

                            <base-input class="input-group-alternative"
                                        placeholder="Password"
                                        type="password"
                                        addon-left-icon="ni ni-lock-circle-open"
                                        v-model="model.password"
										@keydown.enter="login">
                            </base-input>

                            <base-checkbox class="custom-control-alternative">
                                <span class="text-muted">Remember me</span>
                            </base-checkbox>
                            <div class="text-center">
                                <base-button type="primary" class="my-4" @click="login">로그인</base-button>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- <div class="row mt-3">
                    <div class="col-6">
                        <a href="#" class="text-light"><small>Forgot password?</small></a>
                    </div>
                    <div class="col-6 text-right">
                        <router-link to="/register" class="text-light"><small>Create new account</small></router-link>
                    </div>
                </div> -->
            </div>
        </div>
</template>
<script>
  export default {
    name: 'login',
    data() {
      return {
        model: {
          username: '',
          password: ''
        }
      }
    },
	methods: {
    login: function () {
      let username = this.model.username
      let password = this.model.password
      this.$store.dispatch('login', { username, password })
        .then(() => {
          this.$router.push('/dashboard/application')
          })
        .catch(err => {
          console.log(err)
          this.snackbar = true
        }
        )
    }
  },
  }
</script>
<style>
</style>
