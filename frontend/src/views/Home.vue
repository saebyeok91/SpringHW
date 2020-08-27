<template>
  <v-responsive style="aspect-ratio: 16:9">
  <v-main id="inspire">
    <v-container fluid style="padding: 5px">
      <v-img src="@/assets/1.jpg" max-height="700px">
      <div id="header">
        <h1>NEW NORMAL</h1>
      </div>
      <v-row align="center" justify="center">
        <v-col sm="4" id="login">
          <v-text-field id="id" label="ID" type="email" required v-model="email" :rules="idRules" />
          <v-text-field id="pw" label="PASSWORD" type="password" required v-model="password" :rules="passwordRules" />
          <v-btn style="margin-right: 60px;" @click="OnSubmit" :disabled="!valid">LOG IN</v-btn>
          <v-btn style="margin-right: 60px;" next to="/signUp">REGISTER</v-btn>
          <v-btn next to="/adminSetup">admin</v-btn>
         </v-col>
      </v-row>
      </v-img>
    </v-container>
    <v-container fluid style="padding: 5px">
      <v-row align="start" style="padding: 5px">
        <v-col sm="3.5" style="padding: 5px">
        <div id="text">
          <h2>ABOUT</h2>
        </div>
        <div>
          <h4>본문</h4>
        </div>
        </v-col>
        <v-col sm="3.5" style="padding: 5px">
        <div id="text">
          <h2>HOW TO</h2><br>
        </div>
        <div>
          <h4>본문</h4>
        </div>
        </v-col>
        <v-col sm="3.5" style="padding: 5px">
        <div id="text">
          <h2>JOIN</h2>
        </div>
        <div>
          <h4>본문</h4><br>
        </div>
        <div>
          <v-btn outlined next to="/apply" color="gray">APPLY</v-btn>
        </div>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
  </v-responsive>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  data () {
    return {
      value: false,
      id: '',
      valid: [],
      email: '',
      password: '',
      idRules: [],
      passwordRules: [v => !!v || '비밀번호는 필수입니다.']
    }
  },
  methods: {
    onSubmit (payload) {
      console.log('loginPage onSubmit()')
      this.login(payload).then(res => {
        alert('Login Success')
        this.$router.push({ name: 'success' })
      }).catch(err => {
        // alert(err.response.data.message)
        alert('Something Fail: ', err)
      })
    },
    ...mapActions(['login'])
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
  #header {
    color: white;
    font-family: Ubuntu;
    font-size: 30px;
  }
  #login {
    background-color: rgba(255, 255, 255, 0.9);
  }
  #text {
    height: 80px;
  }
</style>
