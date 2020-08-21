<template>
  <div id="wrap">
    <div id="header">
      <h1>ZIHA'S HOME</h1>
    </div>
    <div id="container">
      <div id="aside">
      <br>
      ID<br>
      PW<br>
    </div>
    <br>
    <div id="contents">
      <input id="test_enter1" type="text" v-model="login.id"><br>
      <input id="test_enter2" type="text" v-model="login.pw"><br>
    </div>
  </div>
  <div id="footer">
    <router-link :to="{name: 'signUp'}" class="nav-link" active-class="active">
      <button id="test_btn1">Sign Up</button>
    </router-link>
    <button id="test_btn2" v-on:click="signIn">Sign In</button>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      login: {
        id: this.id,
        pw: this.pw
      }
    }
  },
  methods: {
    signIn: function () {
      console.log('SIGN_IN')
      // dispatch는 무조건 actions으로 가고 commit은 mutation으로 감

      this.$store.dispatch('signIn', this.login)
    }
  }
}
</script>

<style scoped>
  div {
    //border: 2px solid #000;
    font-family: Ubuntu;
    text-align: center;
    //text-decoration: underline;
    margin: 20px;
    padding: 30px;
  }
  #test_btn1 {
    background-color: #EBF5FF;
    color: black;
    font-family: Ubuntu;
    border: 1px;
    font-size: 20px;
    padding: 4px;
    margin-right: 50px;
  }
  #test_btn2 {
    background-color: #EBF5FF;
    color: black;
    font-family: Ubuntu;
    border: 1px;
    font-size: 20px;
    padding: 4px;
  }
  #header {
   //background-color: #EBF5FF;
  }
  #wrap {
    width: 1200px;
    height: 600px;
    margin: 0 auto;
  }
  #container{
    height: 190px;
    margin: 50 auto;
    border: 2px solid #000;
  }
  #aside {
    width: 80px;
    height: 80px;
    float: left;
    text-align: left;
    margin-left: 350px;
    background-color: #B8D7FF;
    line-height: 1.3;
  }
  #contents {
    width: 200px;
    float: left;
    height: 80px;
    margin: 5px;
    padding: 40px;
  }
  #footer {
    height: 50px;
    clear: both;
  }
  #test_enter1 {
    border-color: #FFFFFF;
    border-style: solid;
    background-color: #dcdcdc;
    font-size: 17px;
  }
  #test_enter2 {
    border-color: #FFFFFF;
    border-style: solid;
    background-color: #dcdcdc;
    font-size: 17px;
  }
</style>
