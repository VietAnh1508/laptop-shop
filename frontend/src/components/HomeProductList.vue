<template>
  <v-card flat>
    <v-toolbar color="white" card prominent class="navigat">
      <v-card-title class="font-weight-bold subheading">{{ title }}</v-card-title>
      <v-spacer></v-spacer>
      <a v-for="(link, i) in navigateLinks" :key="i">{{ link }}</a>
    </v-toolbar>

    <v-divider></v-divider>

    <v-container fluid>
      <v-layout row wrap>
        <feature-product :product="features[0]"/>
        <product v-for="(product, i) in products" :key="`product-${i}`" :product="product"/>
      </v-layout>
    </v-container>
  </v-card>
</template>

<script>
import FeatureProduct from "./FeatureProduct";
import Produdct from "@/components/Product";

export default {
  components: {
    product: Produdct,
    "feature-product": FeatureProduct
  },
  props: {
    title: {
      type: String
    }
  },
  data() {
    return {
      navigateLinks: [
        "Apple iPhone",
        "Samsung",
        "Màn hình tràn viền",
        "Giá từ 3-5 triệu",
        "Trả góp 0%",
        "Xem tất cả 155 điện thoại"
      ],
      features: [
        {
          image:
            "https://cdn.tgdd.vn/Products/Images/42/161554/Feature/samsung-galaxy-s10-ft-2.jpg",
          title: "Samsung Galaxy S10",
          price: 20990000
        }
      ],
      products: [
        {
          image:
            "https://cdn.tgdd.vn/Products/Images/42/190321/iphone-xs-max-gray-400x400.jpg",
          title: "iPhone Xs Max 64GB",
          price: 28990000,
          discount: 29990000
        },
        {
          image:
            "https://cdn.tgdd.vn/Products/Images/42/114115/iphone-x-64gb-21-400x400.jpg",
          title: "iPhone X 64GB",
          price: 20990000,
          discount: 22990000
        },
        {
          image:
            "https://cdn.tgdd.vn/Products/Images/42/198791/oppo-f11-pro-2nambh-400x400.jpg",
          title: "OPPO F11 Pro",
          price: 8490000,
          promo: "Bảo hành 2 năm chính hãng"
        },
        {
          image:
            "https://cdn.tgdd.vn/Products/Images/42/192003/samsung-galaxy-a9-2018-blue-400x400.jpg",
          title: "Samsung Galaxy A9 2018",
          price: 8990000,
          discount: 12490000,
          promo: "Thu cũ đổi mới tiết kiệm đến 12.5 triệu"
        },
        {
          image:
            "https://cdn.tgdd.vn/Products/Images/42/167150/nokia-61-plus-2-400x400.jpg",
          title: "Nokia 6.1 Plus",
          price: 4790000,
          discount: 5990000
        },
        {
          image:
            "https://cdn.tgdd.vn/Products/Images/42/198985/huawei-p30-lite-1-400x400.jpg",
          title: "Huawei P30 Lite",
          price: 7490000,
          promo: "Cơ hội trúng 100 giải tiền mặt với tổng giá trị 1 tỷ đồng"
        },
        {
          image:
            "https://cdn.tgdd.vn/Products/Images/42/167535/xiaomi-redmi-note-7-blue-18thangbh-400x400.jpg",
          title: "Xioami Redmi Note 7",
          price: 4990000
        },
        {
          image:
            "https://cdn.tgdd.vn/Products/Images/42/199041/vivo-v15-6-400x400.jpg",
          title: "Vivo V15",
          price: 7990000
        }
      ]
    };
  },
  methods: {
    formatPrice(price) {
      return price.toLocaleString() + "đ";
    }
  }
};
</script>

<style scoped>
.navigat a {
  margin: 0 5px;
}
</style>
