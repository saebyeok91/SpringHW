export default {
  LoginInfo (state) {
    return state.weight
  },
  IsLogin (state) {
    return state.accessToken.length > 0 && !!state.myinfo
  }
}
