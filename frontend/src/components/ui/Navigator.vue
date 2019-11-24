<template>
  <v-app-bar color="white" flat prominent height="45px" class="navigat">
    <v-toolbar-title class="font-weight-bold subheading">
      {{ title }}
    </v-toolbar-title>

    <v-spacer></v-spacer>

    <a v-for="(link, i) in links" :key="i">{{ link }}</a>
  </v-app-bar>
</template>

<script>
export default {
  props: {
    title: {
      type: String
    },
    links: {
      type: Array
    }
  }
};
</script>

<style scoped>
.navigat {
  margin: 0;
  padding: 0;
}

.navigat a {
  margin: 0 5px;
}
</style>
