<template>
  <div id="app">
    <notifications></notifications>
    <router-view/>
  </div>
</template>
<script>
// checks to see if auth jwt token is valid or has expired, if it gets back 401 error log out
export default {
  created: async function () {
    // 최초 리딩시 리플레시 토큰이 있으면 한번 요청한다.
    const refreshToken = localStorage.getItem('refreshToken') || null
    if (refreshToken != null) {
      await this.$store.dispatch('refreshtoken')
    }
  }
}
</script>