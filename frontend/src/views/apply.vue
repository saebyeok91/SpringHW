<template>
  <v-responsive style="aspect-ratio: 16:9">
    <v-main id="inspire">
      <v-container fluid style="padding: 5px">
        <v-img src="@/assets/2.jpeg" max-height="870px">
          <div id="header">
            <h1>JOIN US</h1>
          </div>
            <v-row align="center" justify="center">
              <v-col sm="4" id="login">
                <v-text-field id="artist" label="ARTIST NAME" />
                <v-text-field id="name" label="NAME" />
                <v-text-field id="email" label="EMAIL" />
                <v-text-field id="social" label="ARTIST PAGE"
                  hint="( SOUNDCLOUD / BANDCAMP / YOUTUBE )"
                  persistent-hint />
                <v-text-field id="phone" label="PHONE" />
                <v-btn style="margin-right: 30px;" type="submit" color="gray">APPLY</v-btn>
                <v-btn style="margin-left: 30px;" next to="/" type="submit" color="gray">CANCEL</v-btn>
               </v-col>
            </v-row>
        </v-img>
      </v-container>
    </v-main>
  </v-responsive>
</template>

<script>
</script>

<style scoped>
  div {
    //border: 2px solid black;
    font-family: Ubuntu;
    text-align: center;
    //text-decoration: underline;
    margin: 10px;
    padding: 20px;
  }
  #header {
    color: white;
    font-family: Ubuntu;
    font-size: 30px;
  }
  #login {
    background-color: rgba(255, 255, 255, 0.9);
  }
  #text {
    height: 80px;
  }
</style>
