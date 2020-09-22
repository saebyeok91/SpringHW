<template>
  <Layout>
    <template #content>
      <div id="text"><h2>UPCOMING EVENT</h2>
      </div>
      <div id="info">
        <h3>SAEBYEOK</h3><br>
        <h3>2020.09.19 SAT 8PM</h3>
        <h3>#POP #IDM #SINGERSONGWRITER</h3>
      </div>
        <v-img src="@/assets/3.jpg" max-height="500px"></v-img>
      <div id="info">
        <h3>IZIHA</h3><br>
        <h3>2020.09.26 SAT 9PM</h3>
        <h3>#EXPERIMENTAL #ELECTRONIC </h3>
      </div>
        <v-img src="@/assets/4.jpg" max-height="500px"></v-img>
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
  div {
    //border: 2px solid black;
  }
  #text {
    height: 60px;
    padding: 20px;
    color: black;
  }
  #info {
    height: 150px;
    padding: 20px;
    color: #90A4AE;
  }
</style>
