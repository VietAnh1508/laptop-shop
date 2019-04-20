<template>
  <v-card>
    <v-toolbar color="white" card prominent class="navigat">
      <v-card-title class="font-weight-bold subheading">{{ title }}</v-card-title>
      <v-spacer></v-spacer>
      <a v-for="(link, i) in navigateLinks" :key="i">{{ link }}</a>
    </v-toolbar>

    <v-divider></v-divider>

    <v-container fluid>
      <v-layout row wrap>
        <feature-product :product="feature"/>
        <product v-for="(product, i) in products" :key="`product-${i}`" :product="product"/>
      </v-layout>
    </v-container>
  </v-card>
</template>

<script>
import FeatureProduct from "./FeatureProduct";
import Produdct from "./Product";

export default {
  components: {
    product: Produdct,
    "feature-product": FeatureProduct
  },
  props: {
    title: {
      type: String
    },
    navigateLinks: {
      type: Array
    },
    feature: {
      type: Object
    },
    products: {
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
