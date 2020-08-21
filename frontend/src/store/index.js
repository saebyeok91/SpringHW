import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '../router'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
    successUserInfo: function ({ commit }, msg) {
      console.log('msg : ' + msg)
      if (msg === 'ok') {
        router.push({ name: 'Success' })
      } else {
        alert('이미 존재하는 ID입니다')
      }
    },
    failUserInfo: function () {
      console.log('failUserInfo')
    },
    successRegister: function ({ commit }, msg) {
      console.log('msg : ' + msg)
      if (msg === 'ok') {
        router.push({ name: 'Success' })
      } else {
        alert('ID/ PW를 확인해주세요')
      }
    }
  },
  actions: {
    signIn ({ commit }, login) {
      console.log('id : ' + login.id)
      console.log('pw : ' + login.pw)

      var header = {
        'Content-Type': 'application/json'
      }

      axios.post('http://localhost:1234/signIn', login, header).then((res) => {
        if (res.status === 200) {
          console.log('login data:' + res.data)
          commit('successUserInfo', res.data)
        }
      }).catch((res) => {
        commit('failUserInfo')
      })
    },
    signUp ({ commit }, member) {
      console.log('id: ' + member.id)
      console.log('pw: ' + member.pw)
      console.log('nickName: ' + member.nickName)
      console.log('email: ' + member.email)

      var header = {
        'Content-Type': 'application/json'
      }

      axios.post('http://localhost:1234/overlap', member, header).then((res) => {
        if (res.status === 200) {
          console.log('login data:' + res.data)
          commit('successRegister')
        }
      })
    }
  },
  modules: {
  }
})
