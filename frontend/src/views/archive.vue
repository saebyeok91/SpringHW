<template>
  <Layout>
    <template #content>
    <h2>PREVIOUS EVENTS</h2>
    <v-row justify="end">
      <v-col cols="12" sm="6">
      </v-col>
      <v-spacer>
      </v-spacer>
      <v-col>
        <v-text-field v-model="search" append-icon="mdi-magnify"
          label="Search" single-line hide-details>
        </v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" sm="6">
      </v-col>
      <v-spacer></v-spacer>
      <v-col align="right">
        <v-btn to="/archiveRegister" tile mid depressed style="width: 90px;">ADD
        </v-btn>
      </v-col>
    </v-row>
    <v-data-table
      :headers="headers"
      :footer-props="footerProps"
      :items="lists"
      :search="search">
    </v-data-table>
    </template>
  </Layout>
</template>

<script>
import Layout from '../components/Layout'
import { mapState, mapActions } from 'vuex'

export default {
  components: { Layout },
  computed: {
    ...mapState({
      lists: state => state.lists
    })
  },
  data: function () {
    return {
      search: '',
      headers: [
        { text: 'No', align: 'start', value: 'boardNo' },
        { text: 'Artist', value: 'artist' },
        { text: 'Date', value: 'date' },
        { text: 'Link', value: 'link' },
        { text: 'Social', value: 'social' }
      ],
      footerProps: {
        itemsPerPageText: '',
        lastIcon: '$last',
        nextIcon: '$next',
        itemsPerPageOptions: []
      }
    }
  },
  mounted () {
    this.fetchArchive()
  },
  methods: {
    ...mapActions(['fetchArchive'])
  }
}
</script>

<style>
div {
  //border: 2px solid black;
}
</style>
