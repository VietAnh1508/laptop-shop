<template>
  <div>
    <toolbar/>
    <v-container fluid>
      <v-layout row wrap>
        <v-flex xs8 class="pr-2 mb-2">
          <v-carousel height="300" hide-delimiters>
            <v-carousel-item v-for="(item, i) in items" :key="i" :src="item.src"></v-carousel-item>
          </v-carousel>
        </v-flex>
        <v-flex xs4>
          <v-layout column>
            <v-flex>
              <v-card flat>
                <v-layout>
                  <v-flex>
                    <v-img
                      width="100"
                      height="70"
                      src="https://cdn.tgdd.vn/Files/2019/04/08/1159604/samsung-galaxy-note-10-1_801x450-100x100.jpg"
                    ></v-img>
                  </v-flex>
                  <v-flex>
                    <v-card-title primary-title>
                      <div class="headline">Samsung sẽ ra mắt...</div>
                      <div>25 phút trước</div>
                    </v-card-title>
                  </v-flex>
                </v-layout>
              </v-card>
            </v-flex>
            <v-flex class="my-2">
              <v-img src="https://cdn.tgdd.vn/qcao/28_03_2019_14_15_08_Note-8-398-110.png"></v-img>
            </v-flex>
            <v-flex class="my-2">
              <v-img src="https://cdn.tgdd.vn/qcao/29_03_2019_10_59_44_Sticky-iPhone-398-110.png"></v-img>
            </v-flex>
          </v-layout>
        </v-flex>
        <v-flex xs12 class="my-2">
          <v-img src="https://cdn.tgdd.vn/qcao/09_04_2019_22_57_06_1200-75.png"></v-img>
        </v-flex>
        <v-flex xs12 class="mb-2">
          <v-card flat>
            <v-card-title>
              <span>15 KHUYẾN MÃI HOT NHẤT</span>
            </v-card-title>
            <v-divider></v-divider>
            <v-window>
              <v-window-item>
                <div>
                  <h3>title 1</h3>
                  <span>content 1</span>
                </div>
              </v-window-item>
              <v-window-item>
                <div>
                  <h3>title 2</h3>
                  <span>content 2</span>
                </div>
              </v-window-item>
              <v-window-item>
                <div>
                  <h3>title 3</h3>
                  <span>content 3</span>
                </div>
              </v-window-item>
            </v-window>
            <v-card-actions>
              <v-btn flat color="black">
                <v-icon>mdi-chevron-left</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-flex>
        <v-flex xs12>
          <home-product-list title="ĐIỆN THOẠI NỔI BẬT NHẤT"/>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import Toolbar from "@/components/Toolbar";
import HomeProductList from "@/components/HomeProductList";

export default {
  components: {
    toolbar: Toolbar,
    "home-product-list": HomeProductList
  },
  data() {
    return {
      items: [
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

<style scoped>
</style>
