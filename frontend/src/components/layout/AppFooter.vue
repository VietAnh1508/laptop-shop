<template>
  <v-footer height="auto">
    <v-layout justify-center row wrap>
      <v-flex xs3>
        <v-list>
          <v-list-item
            v-for="(link, index) in col1"
            :key="`col1-${index}`"
            route
            :to="link.route"
            target="_blank"
          >
            <v-list-item-content>
              <v-list-item-title>{{ link.text }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-flex>
      <v-flex xs3>
        <v-list>
          <v-list-item
            v-for="(link, index) in col2"
            :key="`col2-${index}`"
            route
            :to="link.route"
            target="_blank"
          >
            <v-list-item-content>
              <v-list-item-title>{{ link.text }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-flex>
      <v-flex xs3>
        <v-list>
          <v-list-item v-for="(text, index) in col3" :key="`col3-${index}`">
            <v-list-item-content v-text="text"></v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <i class="icon-bct"></i>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-flex>
      <v-flex xs3>
        <v-list>
          <v-list-item>
            <v-list-item-content>
              <v-container>
                <v-layout row>
                  <v-flex xs6>
                    <a class="fb-link" href="#">
                      <i class="icon-fb"></i>
                      <span>3.3tr</span>
                    </a>
                  </v-flex>
                  <v-divider vertical></v-divider>
                  <v-flex xs6>
                    <a class="ytb-link" href="#">
                      <i class="icon-ytb"></i>
                      <span>576.6k</span>
                    </a>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>Website cùng tập đoàn</v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <i class="iconlogo-dmx"></i>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <i class="iconlogo-bhx"></i>
            </v-list-item-content>
          </v-list-item>
          <v-list-item></v-list-item>
        </v-list>
      </v-flex>
      <v-flex xs12 py-3 text-xs-center class="caption"
        >&copy; 2019. Công ty cổ phần Thế Giới Di Động. GPDKKD: 0303217354 do sở
        KH & ĐT TP.HCM cấp ngày 02/01/2007. GP số 56/GP-TTDT do Sở TTTT TP HCM
        cấp ngày 30/07/2018. Địa chỉ: 128 Trần Quang Khải, P. Tân Định, Q.1,
        TP.Hồ Chí Minh. Điện thoại: 18001060. Email: cskh@thegioididong.com.
        Chịu trách nhiệm nội dung: Trần Nhật Linh. Xem chính sách sử dụng
        web</v-flex
      >
    </v-layout>
  </v-footer>
</template>

<script>
export default {
  data() {
    return {
      col1: [
        {
          text: 'Tìm hiểu về mua trả góp',
          route: 'https://www.thegioididong.com/tra-gop'
        },
        {
          text: 'Chính sách bảo hành',
          route: 'https://www.thegioididong.com/bao-hanh'
        },
        {
          text: 'Chính sách đổi trả',
          route: 'https://www.thegioididong.com/chinh-sach-bao-hanh-san-pham'
        },
        {
          text: 'Giao hàng và thanh toán',
          route: 'https://www.thegioididong.com/giao-hang'
        },
        {
          text: 'Xem thêm',
          route: '#'
        }
      ],
      col2: [
        {
          text: 'Giới thiệu công ty',
          route: 'https://mwg.vn/'
        },
        {
          text: 'Tuyển dụng',
          route: 'https://vieclam.thegioididong.com/'
        },
        {
          text: 'Gửi góp ý, khiếu nại',
          route: 'https://www.thegioididong.com/lien-he'
        },
        {
          text: 'Tìm siêu thị (1809 shop)',
          route:
            'https://www.thegioididong.com/he-thong-sieu-thi-the-gioi-di-dong'
        },
        {
          text: 'Xem bản mobile',
          route: '#'
        }
      ],
      col3: [
        'Gọi mua hàng 1800.1060 (7:30 - 22:00)',
        'Gọi khiếu nại 1800.1062 (8:00 - 21:30)',
        'Gọi bảo hành 1800.1064 (8:00 - 21:00)',
        'Hỗ trợ kỹ thuật 1800.1763 (7:30 - 22:00)'
      ]
    };
  }
};
</script>

<style scoped>
a {
  text-decoration: none;
}

.icon-bct {
  background-position: 0 -50px;
  width: 123px;
  height: 38px;
}

.fb-link {
  display: block;
}

[class^='icon-'] {
  background-image: url(https://cdn.thegioididong.com/v2015/Content/desktop/images/V4/icondesktop@1x.png?v=001);
  background-repeat: no-repeat;
}

.icon-fb {
  background-position: 0 -30px;
  width: 13px;
  height: 13px;
  margin-right: 3px;
}

.icon-ytb {
  background-position: -16px -30px;
  width: 17px;
  height: 13px;
  margin-right: 3px;
}

[class^='iconlogo-'] {
  background-image: url(https://www.thegioididong.com/Content/desktop/images/V4/logosprite1x.png);
  background-repeat: no-repeat;
}

.iconlogo-dmx {
  background-position: -109px 0;
  width: 111px;
  height: 19px;
}

.iconlogo-bhx {
  background-position: -220px 0;
  width: 110px;
  height: 19px;
}
</style>
