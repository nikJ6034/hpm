// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import '@babel/polyfill'
import Vue from 'vue'

// Components
import './components'

// Plugins
import './plugins'

// Sync router with store
import { sync } from 'vuex-router-sync'

// Application imports
import App from './App'
import i18n from '@/i18n'
import router from '@/router'
import store from '@/store'
import Vuetify from 'vuetify'
import theme from './plugins/theme'
import 'vuetify/dist/vuetify.min.css'
import '@mdi/font/css/materialdesignicons.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import axios from 'axios'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// import 'tui-grid/dist/tui-grid.css'
// import { Grid } from '@toast-ui/vue-grid'

// Install BootstrapVue
// Vue.use(Grid)

// Install BootstrapVue
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

Vue.prototype.$http = axios
// Sets the default url used by all of this axios instance's requests
axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.headers.get['Accept'] = 'application/json'
//axios.defaults.headers.get['Content-Type'] = 'application/json'

const token = localStorage.getItem('token')
if (token) {
  axios.defaults.headers.common['Authorization'] = 'Bearer ' + token
}

// Response interceptor for API calls
// 리플레시 토큰 요청
axios.interceptors.response.use((response) => {
  return response
}, 
async function (error) {
  const originalRequest = error.config;
  if (error.response.status === 401 && !originalRequest._retry) {
    console.log('토큰 만료')
    originalRequest._retry = true;
    const refreshToken = localStorage.getItem('refreshToken')||null;
    if(refreshToken != null){
      const token = await store.dispatch('refreshtoken')
      originalRequest.headers['Authorization'] = 'Bearer ' + token;
      return await axios(originalRequest);
    }else{
      return Promise.reject(error);
    }
  }
  return Promise.reject(error);
});




// Sync store with router
sync(store, router)

Vue.use(Vuetify, {
  iconfont: 'mdi',
  theme
})

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
