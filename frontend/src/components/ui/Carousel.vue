<template>
  <v-carousel height="300" hide-delimiters>
    <v-carousel-item v-for="(item, i) in banner" :key="i" :src="item.src"></v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  data() {
    return {
      banner: [
        {
          src:
            "https://cdn.tgdd.vn/qcao/29_03_2019_10_01_26_Oppo-f11pro-800-300.png"
        },
        {
          src: "https://cdn.tgdd.vn/qcao/08_04_2019_15_17_40_800-300.png"
        },
        {
          src:
            "https://cdn.tgdd.vn/qcao/04_04_2019_21_02_58_Realme-3-800-300.png"
        }
      ]
    };
  },
  methods: {
    next() {
      this.onboarding =
        this.onboarding + 1 === this.length ? 0 : this.onboarding + 1;
    },
    prev() {
      this.onboarding =
        this.onboarding - 1 < 0 ? this.length - 1 : this.onboarding - 1;
    }
  }
};
</script>
