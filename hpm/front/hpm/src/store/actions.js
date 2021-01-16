// https://vuex.vuejs.org/en/actions.html
import axios from 'axios'
import jwt_decode from "jwt-decode";
import router from '@/router'

// The login action passes vuex commit helper that we will use to trigger mutations.
export default {
  login ({ commit }, userData) {
    return new Promise((resolve, reject) => {
      commit('auth_request')
      axios.post('/oauth2/callback', { memberId: userData.username, memberPassword: userData.password })
        .then(response => {
          const token = response.data.access_token
          const refreshToken = response.data.refresh_token
          var decoded = jwt_decode(token);
          const user = { 'memberId': decoded.user_name, 'name': decoded.name, 'authorities':decoded.authorities }
          // storing jwt in localStorage. https cookie is safer place to store
          localStorage.setItem('refreshToken', refreshToken )
          //axios.defaults.headers.common['Authorization'] = 'Bearer ' + token
          // mutation to change state properties to the values passed along
          commit('auth_success', { token, user })
          resolve(response)
        })
        .catch(err => {
          console.log('login error')
          commit('auth_error')
          localStorage.removeItem('refreshToken')
          reject(err)
        })
    })
  },
  logout ({ commit }) {
    return new Promise((resolve, reject) => {
      commit('logout')
      localStorage.removeItem('refreshToken')
      delete axios.defaults.headers.common['Authorization']
      resolve()
    })
  },
  async refreshtoken ({ commit }) {
    axios.defaults.headers.common['Authorization'] = ''
    return await axios.post('/oauth2/token/refresh', { refresh_token: localStorage.getItem('refreshToken') })
      .then(response => {
        const token = response.data.access_token
        const refreshToken = response.data.refresh_token
        var decoded = jwt_decode(token);
        const user = { 'memberId': decoded.user_name, 'name': decoded.name, 'authorities':decoded.authorities }
        localStorage.setItem('refreshToken', refreshToken )
        //axios.defaults.headers.common['Authorization'] = 'Bearer ' + token
        commit('auth_success', { token, user })
        return token;
      })
      .catch(error => {
        console.log('refresh token error')
        commit('logout')
        localStorage.removeItem('refreshToken')
        router.push(`/`)
      })
  },
  getTableList ({ commit }, tableName) {
    this.$http.get(`/${tableName}`)
      .then(response => {
        console.log(response)
        let tableList = response.data.Keys
        commit('setTableList', { tableList })
      })
      .catch(error => console.log(error))
  },
  updateTableItem ({ commit }, tableData) {
    return new Promise((resolve, reject) => {
      let httpmethod = tableData.method
      axios({ url: `/${tableData.endpoint}`, method: httpmethod, data: tableData.tableItem })
        .then(response => {
          resolve(response)
        })
        .catch(error => {
          console.log(error)
          reject(error)
        })
    })
  }

  // autoRefreshToken ({ commit }) {
  //   return new Promise((resolve, reject) => {
  //     setInterval(function () {
  //       // code goes here that will be run every 20 minutes.
  //       axios.get('/refresh')
  //         .then(response => {
  //           const token = response.data.access_token
  //           localStorage.setItem('token', token)
  //           axios.defaults.headers.common['Authorization'] = 'Bearer ' + token
  //           commit('auth_success', { token })
  //           resolve(response)
  //         })
  //         .catch(error => {
  //           console.log('refresh token error')
  //           console.log(error)
  //           reject(error)
  //         })
  //     }, 1200000)
  //   }
  //   )
  // },

}
