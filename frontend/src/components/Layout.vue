<template>
  <v-main>
    <v-app-bar height="100px"
      app
      flat
      color="#FFFFFF"
      >
      <v-toolbar-title><div @click="Success"><h1>NEW NORMAL</h1></div></v-toolbar-title>
      <v-spacer></v-spacer>
      <slot name="menubar">
        <v-btn @click="Success" text color="black"
          style="padding: 10px; width: 90px;">Home</v-btn>
        <v-btn href="http://localhost:3000" text color="black"
          style="padding: 10px; width: 90px;">Stream</v-btn>
        <v-btn @click="Archive" text color="black"
          style="padding: 10px; width: 90px;">Archive</v-btn>
        <v-btn @click="onClickLogout" text color="black"
          style="padding: 10px; width: 90px;">Logout</v-btn>
      </slot>
    </v-app-bar>

    <v-container fluid>
      <v-content id="content" style="padding: 30px">
        <slot name="content" class="font">
        </slot>
      </v-content>
    </v-container>

    <v-footer
      app
      color="white"
      class="black--text"
    >
      <v-spacer></v-spacer>
        <span>&copy; ZIHA</span>
    </v-footer>
  </v-main>
</template>

<script>
import router from '../router'
import { mapActions } from 'vuex'

export default {
  data: () => ({
    drawer: false,
    left: false
  }),
  methods: {
    Success () {
      (window.location.pathname !== '/success') ? router.push('/success') : router.go(0)
    },
    Calender () {
      (window.location.pathname !== '/calender') ? router.push('/calender') : router.go(0)
    },
    Archive () {
      (window.location.pathname !== '/archive') ? router.push('/archive') : router.go(0)
    },
    onClickLogout () {
      this.logout()
      alert('Success Logout')
      this.$router.push({ name: 'home' })
    },
    ...mapActions(['logout'])
  }
}

</script>
