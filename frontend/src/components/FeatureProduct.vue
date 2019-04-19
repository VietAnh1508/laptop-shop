<template>
  <v-flex class="feature">
    <v-card>
      <v-img :src="product.image"/>
      <v-card-title primary-title>
        <div>
          <div>{{ product.title }}</div>
          <div class="price">
            <strong class="red--text">{{ formatPrice(product.price) }}</strong>
          </div>
        </div>
      </v-card-title>
    </v-card>
  </v-flex>
</template>

<script>
import productMixin from "@/mixins/productMixin";

export default {
  props: {
    product: {
      type: Object
    }
  },
  mixins: [productMixin]
};
</script>

<style scoped>
.feature {
  width: 40%;
  float: left;
}

.price {
  display: block;
  margin: 5px 0;
}
</style>
