<template>
  <Layout>
    <template #content>
    <v-responsive style="aspect-ratio: 16:9">
      <v-row align="center" justify="center">
        <iframe width="1280" height="730" src="https://www.youtube.com/embed/O9UQCud7rg4"
        frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
      </v-row>
    </v-responsive>
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
  }
}
</script>

<style>
</style>
