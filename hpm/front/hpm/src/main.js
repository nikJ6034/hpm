/*!

=========================================================
* Vue Argon Dashboard - v1.0.0
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-dashboard
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from "./store";
import './registerServiceWorker'
import ArgonDashboard from './plugins/argon-dashboard'
import axios from 'axios'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'tui-date-picker/dist/tui-date-picker.css'
import 'tui-time-picker/dist/tui-time-picker.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import vueMoment from 'vue-moment' 

Vue.config.productionTip = false

// Install BootstrapVue
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
Vue.use(vueMoment)
Vue.use(ArgonDashboard)
Vue.use(router);
Vue.use(store);
Vue.prototype.$http = axios

// Sets the default url used by all of this axios instance's requests
axios.defaults.baseURL = process.env.VUE_APP_API_URL
axios.defaults.headers.get['Accept'] = 'application/json'

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
  const originalRequest = error.config
  if (error.response.status === 401 && !originalRequest._retry) {
    originalRequest._retry = true
    const refreshToken = localStorage.getItem('refreshToken') || null
    if (refreshToken != null) {
      const token = await store.dispatch('refreshtoken')
      if (token) {
        originalRequest.headers['Authorization'] = 'Bearer ' + token
        return await axios(originalRequest)
      } else {
        router.push(`/`)
      }
    } else {
      //router.push(`/`)
      return Promise.reject(error)
    }
  }
  //router.push(`/`)
  return Promise.reject(error)
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
