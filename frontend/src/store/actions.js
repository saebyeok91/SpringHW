import {
  SET_ACCESS_TOKEN,
  SET_MY_INFO,
  /* eslint-disable no-unused-vars */
  DESTROY_ACCESS_TOKEN,
  DESTROY_MY_INFO
} from './mutation-types'

import axios from 'axios'
import router from '../router'

export default {
  login ({ commit }, payload) {
    console.log('actions login')
    return axios.post(`http://localhost:7777/api/authenticate?username=${payload.userid}&password=${payload.password}`, {
      username: payload.userid,
      password: payload.password
    }).then(res => {
      // 토큰을 얻음
      console.log('actions after post')
      const { authorization } = res.headers
      // 7개를 잘라냄.
      const accessToken = authorization.substring(7)
      // SET_ACCESS_TOKEN에 accessToken을 기록
      commit(SET_ACCESS_TOKEN, accessToken)

      return axios.get('http://localhost:7777/users/myinfo')
    }).then(res => {
      console.log('After Get Auth Info')
      commit(SET_MY_INFO, res.data)
    })
  },
  loginByToken ({ commit }, token) {
  // Token을 얻어옴 - 토큰은 로그인 할 때 생김.
    commit(SET_ACCESS_TOKEN, token)
    return axios.get('http://localhost:1234/users/myinfo')
      .then(res => {
        commit(SET_MY_INFO, res.data)
      })
  },
  logout ({ commit }) {
    commit(DESTROY_MY_INFO)
    commit(DESTROY_ACCESS_TOKEN)
  }
}
