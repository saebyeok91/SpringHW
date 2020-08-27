<template>
  <div>
  <br><br><br>
    <v-row align="center" justify="center">
      <h2>Register First Administrator</h2>
    </v-row>
    <form @submit.prevent="submit">
      <v-row align="center" justify="center">
        <v-col sm="4" id="login">
          <v-text-field id="id" label="ID" v-model="userId" />
          <v-text-field id="pw" label="PASSWORD" type="password" v-model="userPw" />
          <v-text-field id="name" label="NAME" v-model="userName" />
        </v-col>
      </v-row>
      <v-row align="center" justify="center">
        <v-col sm="1">
          <v-btn style="padding: 20px" type="submit">REGISTER</v-btn>
        </v-col>
      </v-row>
    </form>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'adminSetup',
  data () {
    return {
      userId: '',
      userName: '',
      userPw: ''
    }
  },
  methods: {
    submit () {
      const { userId, userName, userPw } = this
      console.log('payload: ' + this.userId)
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
