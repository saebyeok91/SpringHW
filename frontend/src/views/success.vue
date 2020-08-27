<template>
  <Layout>
    <template #content>
      <!-- <v-img src="@/assets/3.jpg" max-height="700px"> -->
        <v-row align="center" style="padding: 20px">
          <v-col sm="3.5" style="padding: 20px" align="center">
            <div id="text">
              <h2>ABOUT</h2>
            </div>
            <div id="text">
              <h4>본문</h4>
            </div>
          </v-col>
          <v-col sm="3.5" style="padding: 20px" align="center">
            <div id="text">
              <h2>HOW TO</h2><br>
            </div>
            <div id="text">
              <h4>본문</h4>
            </div>
          </v-col>
          <v-col sm="3.5" style="padding: 20px" align="center">
            <div id="text">
              <h2>JOIN</h2>
            </div>
            <div id="text">
              <h4>본문</h4><br>
            </div>
            <div>
              <v-btn outlined next to="/apply" color="black">APPLY</v-btn>
            </div>
          </v-col>
        </v-row>
        <!-- </v-img> -->
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
  #title {
    padding: 50px;
    align: center;
  }
  #text {
    //background-color: rgba(255, 255, 255, 0.9);
    height: 80px;
    padding: 20px;
    color: black;
  }
</style>
