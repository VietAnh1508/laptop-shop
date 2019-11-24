import Vue from 'vue';
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);

export default new Vuetify({
  dark: true,
  themes: {
    dark: {
      prime: '#333'
    },
    light: {
      prime: '#DF8421'
    }
  },
  icons: {
    iconfont: 'mdi'
  }
});
