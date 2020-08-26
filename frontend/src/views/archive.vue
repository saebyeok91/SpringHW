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
    <v-data-table
      :headers="headers"
      :footer-props="footerProps"
      :search="search">
    </v-data-table>
    </template>
  </Layout>
</template>

<script>
import Layout from '../components/Layout'
import { mapState } from 'vuex'
export default {
  components: { Layout },
  computed: {
    ...mapState({
      lists: state => state.lists
    })
  },
  methods: {
  },
  data: function () {
    return {
      search: '',
      headers: [
        { text: 'No', align: 'start', value: 'Numbers' },
        { text: 'Date', value: 'Date' },
        { text: 'Artist', value: 'Artist' }
      ],
      footerProps: {
        itemsPerPageText: '',
        lastIcon: '$last',
        nextIcon: '$next',
        itemsPerPageOptions: []
      }
    }
  }
}
</script>

<style>
div {
  //border: 2px solid black;
}
</style>
