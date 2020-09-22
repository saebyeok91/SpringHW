import {
  SET_ACCESS_TOKEN,
  SET_MY_INFO,
  DESTROY_ACCESS_TOKEN,
  DESTROY_MY_INFO,
  FETCH_ARCHIVE
} from './mutation-types'

import axios from 'axios'
import cookies from 'vue-cookies'

export default {
  [SET_ACCESS_TOKEN] (state, accessToken) {
    // 토큰을 등록
    if (accessToken) {
      state.accessToken = accessToken

      // AXIOS에 토큰을 등록해서 다음에 로그인할 때 만들어놓은 정보를 비교해서 처리.
      axios.defaults.headers.common.Authorization = `Bearer ${accessToken}`
      console.log('axios Auth: ' + axios.defaults.headers.common.Authorization)

      cookies.set('accessToken', accessToken, 'null')
    }
  },
  [SET_MY_INFO] (state, myinfo) {
    if (myinfo) {
      state.myinfo = myinfo
    }
  },
  [DESTROY_ACCESS_TOKEN] (state) {
    state.accessToken = ''
    delete axios.defaults.headers.common.Authorization
    cookies.remove('accessToken')
  },
  [DESTROY_MY_INFO] (state) {
    state.myinfo = null
  },
  [FETCH_ARCHIVE] (state, archive) {
    state.lists = archive
  }
}
