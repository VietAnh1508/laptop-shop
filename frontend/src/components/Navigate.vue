<template>
  <v-toolbar color="white" card prominent class="navigat">
    <v-card-title class="font-weight-bold subheading">{{ title }}</v-card-title>
    <v-spacer></v-spacer>
    <a v-for="(link, i) in links" :key="i">{{ link }}</a>
  </v-toolbar>
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
.navigat a {
  margin: 0 5px;
}
</style>
