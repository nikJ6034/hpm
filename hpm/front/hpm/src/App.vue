<template>
  <transition mode="out-in">
    <router-view/>
  </transition>
</template>

<style lang="scss">
  @import "@/styles/index.scss";

  /* Remove in 1.2 */
  .v-datatable thead th.column.sortable i {
    vertical-align: unset;
  }
</style>
<script>
// checks to see if auth jwt token is valid or has expired, if it gets back 401 error log out
export default {
  created: async function () {

    //최초 리딩시 리플레시 토큰이 있으면 한번 요청한다.
    const refreshToken = localStorage.getItem('refreshToken')||null;
    if(refreshToken != null){
      await this.$store.dispatch('refreshtoken')  
    }
    /*this.$http.interceptors.response.use((response) => {
      return response
    }, (error) => {
      if (error.response.status === 401) {
        console.log(error.response)
        if (this.$store.getters.authorized) {
          //this.$store.dispatch('refreshtoken')
        } else {
          return Promise.reject(error)
        }
      } else {
        return Promise.reject(error)
      }
    })*/
  /*this.$http.interceptors.request.use(
    async config => {
      const accessToken = localStorage.getItem('token')||null;
      if(accessToken != null){
        config.headers = { 
        'Authorization': `Bearer ${accessToken}`,
        'Accept': 'application/json',
        }
        return config;
      }
      
    },
    error => {
      Promise.reject(error)
  });*/
  }
}
</script>
