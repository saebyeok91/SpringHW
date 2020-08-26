import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '../views/Home.vue'
import signUp from '../views/signUp.vue'
import signIn from '../views/signIn.vue'
import Logout from '../views/Logout.vue'
import archive from '../views/archive.vue'
import calender from '../views/calender.vue'
import stream from '../views/stream.vue'
import success from '../views/success.vue'
import apply from '../views/apply.vue'
import test from '../views/test.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
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
    path: '/test',
    name: 'test',
    component: test
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
