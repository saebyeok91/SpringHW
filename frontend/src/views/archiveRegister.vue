<template>
  <Layout>
    <template #content>
    <h2>ADD PREVIOUS EVENTS</h2>
    <v-row justify="center">
      <v-col cols="12" sm="6">
      <form @submit.prevent="onSubmit">
        <v-text-field v-model="artist" id="artist" label="Artist" />
          <v-menu
            ref="menu1"
            v-model="menu1"
            :close-on-content-click="false"
            transition="scale-transition"
            offset-y
            max-width="290px"
            min-width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="dateFormatted"
                label="Date"
                v-bind="attrs"
                @blur="date = parseDate(dateFormatted)"
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker v-model="date" no-title @input="menu1 = false"></v-date-picker>
          </v-menu>
        <v-text-field v-model="link" id="link" label="Link" />
        <v-text-field v-model="social" id="social" label="Artist Page" />
        <v-btn type="submit" tile mid depressed style="width: 90px;">ADD
        </v-btn>
      </form>
      </v-col>
    </v-row>
    </template>
  </Layout>
</template>

<script>
import Layout from '../components/Layout'
import axios from 'axios'
import { mapState } from 'vuex'

export default {
  components: { Layout },
  name: 'archiveRegister',
  data: vm => ({
    date: new Date().toISOString().substr(0, 10),
    dateFormatted: vm.formatDate(new Date().toISOString().substr(0, 10)),
    menu1: false,
    artist: '',
    link: '',
    social: ''
  }),
  computed: {
    ...mapState({
      lists: state => state.lists
    }),
    computedDateFormatted () {
      return this.formatDate(this.date)
    }
  },
  watch: {
    date (val) {
      this.dateFormatted = this.formatDate(this.date)
    }
  },
  methods: {
    onSubmit () {
      const { artist, date, link, social } = this
      console.log('payload:' + this.artist)
      axios.post('http://localhost:1234/boards/genPost',
        { artist, date, link, social })
        .then(res => {
          alert('Register Success')
          this.$router.push({
            name: 'archive',
            params: { boardNo: res.data.boardNo.toString() }
          })
        })
        .catch(err => {
          alert(err.response.data)
        })
    },
    formatDate (date) {
      if (!date) return null

      const [year, month, day] = date.split('-')
      return `${year}/${month}/${day}`
    },
    parseDate (date) {
      if (!date) return null

      const [year, month, day] = date.split('/')
      return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
    }
  }
}
</script>
