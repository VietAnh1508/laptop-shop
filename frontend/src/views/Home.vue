<template>
  <div>
    <toolbar/>
    <v-container fluid>
      <v-layout row wrap>
        <v-flex xs8 class="pr-2 mb-2">
          <carousel/>
        </v-flex>

        <v-flex xs4 class="mb-2">
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

        <v-flex xs12 class="mb-2">
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

        <v-flex xs12 class="mb-2">
          <home-product-list :feature="smartphoneFeature" :products="smartphones">
            <navigate
              slot="navigate"
              title="ĐIỆN THOẠI NỔI BẬT NHẤT"
              :links="smartphoneNavigateLinks"
            />
          </home-product-list>
        </v-flex>

        <v-flex xs12 class="mb-2">
          <home-product-list :feature="laptopFeature" :products="laptops">
            <navigate
              slot="navigate"
              title="TABLET - LAPTOP NỔI BẬT NHẤT"
              :links="laptopNavigateLinks"
            />
          </home-product-list>
        </v-flex>

        <v-flex xs12>
          <v-card>
            <navigate title="KHUYẾN MÃI TẠI CÁC WEBSITE THÀNH VIÊN"/>
            <v-divider></v-divider>
            <v-container fluid>
              <v-layout>
                <v-flex xs12>
                  <v-img
                    src="https://cdn.tgdd.vn/qcao/13_04_2019_13_16_59_TGDD-maylanh-banner-desk.jpg"
                  />
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
    <app-footer/>
  </div>
</template>

<script>
import Toolbar from "@/components/Toolbar";
import Carousel from "@/components/Carousel";
import Navigate from "@/components/Navigate";
import HomeProductList from "@/components/HomeProductList";
import AppFooter from "@/components/AppFooter";

export default {
  components: {
    toolbar: Toolbar,
    carousel: Carousel,
    navigate: Navigate,
    "home-product-list": HomeProductList,
    "app-footer": AppFooter
  },
  computed: {
    smartphoneNavigateLinks() {
      return this.$store.state.smartphone.navigateLinks;
    },
    smartphoneFeature() {
      return this.$store.state.smartphone.feature;
    },
    smartphones() {
      return this.$store.state.smartphone.products;
    },
    laptopNavigateLinks() {
      return this.$store.state.laptop.navigateLinks;
    },
    laptopFeature() {
      return this.$store.state.laptop.feature;
    },
    laptops() {
      return this.$store.state.laptop.products;
    }
  }
};
</script>