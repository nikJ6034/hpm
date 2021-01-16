// https://vuex.vuejs.org/en/state.html

export default {
  authStatus: '',
  token: localStorage.getItem('refreshToken') || '',
  user: {},
  tableList: []
}
