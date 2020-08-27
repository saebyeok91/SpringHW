<template>
  <div>
  <br><br><br>
    <v-row align="center" justify="center">
    <h2>Register First Adminstrator</h2>
    </v-row>
    <v-row align="center" justify="center">
      <v-col sm="4" id="login">
        <v-text-field id="id" label="ID" type="email"/>
        <v-text-field id="pw" label="PASSWORD" type="password" required />
        <v-text-field id="name" label="NAME" type="name" required />
      </v-col>
    </v-row>
    <v-row align="center" justify="center">
      <v-col sm="1">
        <v-btn style="padding: 20px" @click="submit">REGISTER</v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'adminSetup',
  components: {
  },
  methods: {
    submit (payload) {
      const { userId, userName, userPw } = payload
      axios.post('http://localhost:1234/users/setup',
        { userId, userName, userPw })
        .then(res => {
          alert('Register Success')
          this.$router.push({
            name: 'Home'
          })
        })
        .catch(err => {
          alert(err.response.data)
        })
    }
  }
}
</script>

<style>
  div {
    //border: 2px solid black;
  }
</style>
