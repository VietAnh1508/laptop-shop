<template>
  <v-card>
    <slot name="navigate"></slot>
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
