<template>
  <v-flex class="col-xs-5ths">
    <v-card class="product">
      <v-img class="product-image" :src="product.image"/>
      <v-card-title primary-title>
        <div>
          <div>{{ product.title }}</div>
          <div class="price">
            <strong class="red--text">{{ formatPrice(product.price) }}</strong>
            <span
              v-if="product.discount !== undefined"
              class="discount"
            >{{ formatPrice(product.discount) }}</span>
          </div>
          <div class="promo">
            <p>{{ product.promo }}</p>
          </div>
        </div>
      </v-card-title>
    </v-card>
  </v-flex>
</template>

<script>
import productMixin from "@/mixins/productMixin";

export default {
  mixins: [productMixin],
  props: {
    product: {
      type: Object
    }
  }
};
</script>

<style scoped>
.product {
  height: 100%;
}

.product-image {
  margin: 0 auto;
  width: 180px;
  height: 180px;
}

.price {
  display: block;
  margin: 5px 0;
}

.discount {
  text-decoration: line-through;
  margin-left: 3px;
}

.promo {
  display: block;
  font-size: 12px;
}
</style>
