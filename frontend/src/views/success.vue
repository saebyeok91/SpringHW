<template>
  <Layout>
    <template #content>
      <v-img src="@/assets/main.jpg" />
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
#title {
  //border: 2px black;
  padding: 50px;
  align: center;
}
</style>
