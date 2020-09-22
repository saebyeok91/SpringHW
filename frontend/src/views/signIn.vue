<template>
  <v-main>
    <v-responsive style="aspect-ratio: 16:9">
    <v-container fluid>
      <v-img src="@/assets/4.jpg" max-height="800px">
      <div id="text">
        <h1>NEW NORMAL</h1>
      </div>
        <v-row align="center" justify="center">
          <v-col sm="4" id="login">
          <form @submit.prevent="onSubmit">
            <v-text-field id="id" label="ID" type="id" v-model="userId"/>
            <v-text-field id="email" label="E-MAIL" type="email" required v-model="email" :rules="emailRules" />
            <v-text-field id="nickName" label="NAME" required v-model="userName" />
            <v-text-field id="pw" label="PASSWORD" type="password" required :rules="passwordRules" v-model="userPw"/>
            <v-btn style="margin-right: 30px;" color="gray" type="submit">REGISTER</v-btn>
            <v-btn style="margin-left: 30px;" next to="/">CANCEL</v-btn>
          </form>
          </v-col>
        </v-row>
      </v-img>
    </v-container>
    </v-responsive>
  </v-main>
</template>

<script>
import axios from 'axios'

export default {
  name: 'signUp',
  data () {
    return {
      value: false,
      userId: '',
      email: '',
      userName: '',
      userPw: '',
      emailRules: [
        v => !!v || '이메일은 필수입니다.',
        v => /.+@.+/.test(v) || '이메일이 유효하지 않습니다.'
      ],
      passwordRules: [v => !!v || '비밀번호는 필수입니다.']
    }
  },
  methods: {
    onSubmit () {
      const { userId, userName, userPw, email } = this
      console.log('payload:' + this.userId)
      axios.post('http://localhost:1234/users/genMember',
        { userId, userName, userPw, email })
        .then(res => {
          alert('Register Success')
          this.$router.push({
            name: 'home'
          })
        })
        .catch(err => {
          alert(err.response.data)
        })
    }
  }
}
</script>

<style scoped>
  div {
    //border: 2px solid black;
    font-family: Ubuntu;
    text-align: center;
    //text-decoration: underline;
    margin: 10px;
    padding: 20px;
  }
  #text {
    color: white;
    font-family: Ubuntu;
    font-size: 30px;
  }
  #login {
    background-color: rgba(255, 255, 255, 0.9);
  }
</style>
