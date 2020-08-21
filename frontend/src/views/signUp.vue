<template>
  <div id="wrap">
    <div id="header">
      <h1>SIGN UP</h1>
    </div>
    <div id="container">
      <div id="aside">
      ID<br>
      PW<br>
      Nickname<br>
      Email
    </div>
    <div id="contents">
      <input id="test_enter1" type="text" v-model="member.id">
      <input id="test_enter2" type="text" v-model="member.pw"><br>
      <input id="test_enter3" type="text" v-model="member.nickName"><br>
      <input id="test_enter4" type="text" v-model="member.email"><br><br>
    </div>
    <div id="overlap">
      <button id="test_btn4">Check</button><br><br>
      <button id="test_btn5">Check</button><br>
      <button id="test_btn6">Check</button><br>
    </div>
  </div>
  <div id="footer">
    <router-link :to="{name: 'About'}" class="nav-link" active-class="active">
      <button id="test_btn3" @click="signUp">Sign Up</button><br>
    </router-link>
    </div>
  </div>
</template>
<script>
export default {
  name: 'signUp',
  data: function () {
    return {
      member: {
        id: '',
        pw: '',
        nickName: '',
        email: ''
      }
    }
  },
  methods: {
    signUp: function () {
      this.$store.dispatch('signUp', this.member)
      // 이렇게하면 store -> index.js의 signUp에 this.member를 넘기게 됨.
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
  #test_btn3 {
    background-color: #EBF5FF;
    color: black;
    font-family: Ubuntu;
    border: 1px;
    font-size: 20px;
    padding: 4px;
    margin-right: 10px;
  }
  #test_btn4 {
    background-color: #EBF5FF;
    color: black;
    font-family: Ubuntu;
    border: 1px;
    font-size: 15px;
    padding: 4px;
    margin-bottom: 5px;
  }
  #test_btn5 {
    background-color: #EBF5FF;
    color: black;
    font-family: Ubuntu;
    border: 1px;
    font-size: 15px;
    padding: 4px;
    margin-bottom: 4px;
  }
  #test_btn6 {
    background-color: #EBF5FF;
    color: black;
    font-family: Ubuntu;
    border: 1px;
    font-size: 15px;
    padding: 4px;
  }
  #header {
   background-color: #EBF5FF;
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
    margin-left: 270px;
    background-color: #B8D7FF;
    line-height: 1.4;
  }
  #contents {
    width: 200px;
    float: left;
    height: 83px;
    margin: 5px;
    padding: 35px;
  }
  #overlap {
    width: 40px;
    height: 100px;
    float: left;
    padding: 20px;
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
  #test_enter3 {
    border-color: #FFFFFF;
    border-style: solid;
    background-color: #dcdcdc;
    font-size: 17px;
  }
  #test_enter4 {
    border-color: #FFFFFF;
    border-style: solid;
    background-color: #dcdcdc;
    font-size: 17px;
  }
</style>
