<template>
  <v-card flat>
    <v-toolbar color="white" card prominent class="navigat">
      <v-card-title class="font-weight-bold">{{ title }}</v-card-title>
      <v-spacer></v-spacer>
      <a v-for="(link, i) in navigateLinks" :key="i">{{ link }}</a>
    </v-toolbar>

    <v-divider></v-divider>

    <v-container fluid>
      <v-layout row wrap>
        <v-flex class="feature" v-for="(item, i) in features" :key="`feature-${i}`">
          <v-card>
            <v-img :src="item.image"/>
            <v-card-title primary-title>
              <div>
                <div>{{ item.title }}</div>
                <div class="price">
                  <strong class="red--text">{{ formatPrice(item.price) }}</strong>
                </div>
              </div>
            </v-card-title>
          </v-card>
        </v-flex>
        <v-flex class="col-xs-5ths" v-for="(product, i) in products" :key="`product-${i}`">
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
      </v-layout>
    </v-container>
  </v-card>
</template>

<script>
export default {
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
.feature {
  width: 40%;
  float: left;
}

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
}

.navigat a {
  margin: 0 5px;
}
</style>
