import Vue from 'vue'
import VueRouter from 'vue-router'

import home from '../views/home.vue'
import signUp from '../views/signUp.vue'
import signIn from '../views/signIn.vue'
import Logout from '../views/Logout.vue'
import archive from '../views/archive.vue'
import calender from '../views/calender.vue'
import stream from '../views/stream.vue'
import success from '../views/success.vue'
import apply from '../views/apply.vue'
import adminSetup from '../views/adminSetup.vue'
import archiveRegister from '../views/archiveRegister.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: home
  },
  {
    path: '/signUp',
    name: 'signUp',
    component: signUp
  },
  {
    path: '/signIn',
    name: 'signIn',
    component: signIn
  },
  {
    path: '/Logout',
    name: 'Logout',
    component: Logout
  },
  {
    path: '/archive',
    name: 'archive',
    component: archive
  },
  {
    path: '/calender',
    name: 'calender',
    components: {
      default: calender
    }
  },
  {
    path: '/stream',
    name: 'stream',
    component: stream
    // beforeEnter (to, from, next) {
    // var loginInfo = store.getters.getLoginInfo
    // var isLogin = store.getters.getIsLogin
    // if (loginInfo !== null && isLogin) {
    //  next()
    // } else {
    // alert('Login이 필요한 서비스입니다')
    // next({ name: home })
    // }
    // }
  },
  {
    path: '/success',
    name: 'success',
    component: success
  },
  {
    path: '/apply',
    name: 'apply',
    component: apply
  },
  {
    path: '/adminSetup',
    name: 'adminSetup',
    component: adminSetup
  },
  {
    path: '/archiveRegister',
    name: 'archiveRegister',
    component: archiveRegister
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
