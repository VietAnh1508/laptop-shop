import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    smartphone: {
      navigateLinks: [
        'Apple iPhone',
        'Samsung',
        'Màn hình tràn viền',
        'Giá từ 3-5 triệu',
        'Trả góp 0%',
        'Xem tất cả 155 điện thoại'
      ],
      feature: {
        image:
          'https://cdn.tgdd.vn/Products/Images/42/161554/Feature/samsung-galaxy-s10-ft-2.jpg',
        title: 'Samsung Galaxy S10',
        price: 20990000
      },
      products: [
        {
          image:
            'https://cdn.tgdd.vn/Products/Images/42/190321/iphone-xs-max-gray-400x400.jpg',
          title: 'iPhone Xs Max 64GB',
          price: 28990000,
          discount: 29990000
        },
        {
          image:
            'https://cdn.tgdd.vn/Products/Images/42/114115/iphone-x-64gb-21-400x400.jpg',
          title: 'iPhone X 64GB',
          price: 20990000,
          discount: 22990000
        },
        {
          image:
            'https://cdn.tgdd.vn/Products/Images/42/198791/oppo-f11-pro-2nambh-400x400.jpg',
          title: 'OPPO F11 Pro',
          price: 8490000,
          promo: 'Bảo hành 2 năm chính hãng'
        },
        {
          image:
            'https://cdn.tgdd.vn/Products/Images/42/192003/samsung-galaxy-a9-2018-blue-400x400.jpg',
          title: 'Samsung Galaxy A9 2018',
          price: 8990000,
          discount: 12490000,
          promo: 'Thu cũ đổi mới tiết kiệm đến 12.5 triệu'
        },
        {
          image:
            'https://cdn.tgdd.vn/Products/Images/42/167150/nokia-61-plus-2-400x400.jpg',
          title: 'Nokia 6.1 Plus',
          price: 4790000,
          discount: 5990000
        },
        {
          image:
            'https://cdn.tgdd.vn/Products/Images/42/198985/huawei-p30-lite-1-400x400.jpg',
          title: 'Huawei P30 Lite',
          price: 7490000,
          promo: 'Cơ hội trúng 100 giải tiền mặt với tổng giá trị 1 tỷ đồng'
        },
        {
          image:
            'https://cdn.tgdd.vn/Products/Images/42/167535/xiaomi-redmi-note-7-blue-18thangbh-400x400.jpg',
          title: 'Xioami Redmi Note 7',
          price: 4990000
        },
        {
          image:
            'https://cdn.tgdd.vn/Products/Images/42/199041/vivo-v15-6-400x400.jpg',
          title: 'Vivo V15',
          price: 7990000
        }
      ]
    },
    laptop: {
      navigateLinks: [
        'Giảm sốc Online',
        'Macbook',
        'Laptop Dell',
        'Laptop ASUS',
        'Laptop HP',
        'Xem tất cả 31 tablet',
        'Xem tất cả 92 laptop'
      ],
      feature: {
        image:
          'https://cdn.tgdd.vn/Products/Images/44/195383/Feature/hp-pavilion-15-cs1009tu-i5-8265u-4gb-1tb-win10-5j-3.jpg',
        title: 'HP Pavilion 15 cs1009TU i5 8265U (5JL43PA)',
        price: 15390000
      },
      products: [
        {
          image:
            'https://cdn.tgdd.vn/Products/Images/44/198795/lenovo-ideapad-530s-14ikb-i7-8550u-8gb-256gb-win10-33397-thumb-400x400.jpg',
          title: 'Lenovo Ideapad 530S 14IKB i7 8550U (81EU00P5VN)',
          price: 20990000,
          promo: 'Tặng balo Lenovo (khuyến mãi) và 3 khuyến mãi khác'
        },
        {
          image:
            'https://cdn.tgdd.vn/Products/Images/522/163813/ipad-wifi-cellular-128gb-2018-thumb-55-400x400.jpg',
          title: 'iPad 2018 Wifi Cellular 128GB',
          price: 13990000,
          discount: 14990000,
          promo:
            'Mua kèm Apple Pencil với giá ưu đãi giảm 500.000đ (không kèm KM khác)'
        },
        {
          image:
            'https://cdn.tgdd.vn/Products/Images/44/193516/asus-x507uf-i5-8250u-4gb-1tb-2gb-mx130-ej121t-thumb-1-400x400.jpg',
          title: 'Asus X507UF i5 8250U 4GB 1TB (EJ121T)',
          price: 14590000,
          promo: 'Tặng Chuột không dây Genius NX 7010 và 2 khuyến mãi khác'
        }
      ]
    }
  }
});
