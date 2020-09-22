<template>
  <form @submit.prevent="submit">
    <v-text-field style="margin: 25px;" id="id" label="ID" v-model="userId" />
    <v-text-field style="margin: 25px;" type="password" id="pw" label="PASSWORD" :rules="passwordRules" v-model="userPw" />
    <v-btn style="margin-right: 25px; margin-left: 12px;" type="submit">LOG IN</v-btn>
    <v-btn style="margin: 25px;" next to="/signUp">REGISTER</v-btn>
    <v-btn style="margin: 25px;" next to="/adminSetup">admin</v-btn>
  </form>
</template>

//props / emit/ v-bind, v-model()

<script>
export default {
  name: 'LoginForm',
  data () {
    return {
      userId: '',
      userPw: '',
      passwordRules: [v => !!v || '비밀번호는 필수입니다.']
    }
  },
  methods: {
    submit () {
      console.log('LoginForm submit()')
      const { userId, userPw } = this
      this.$emit('submit', { userId, userPw })
    }
  }
}
</script>
