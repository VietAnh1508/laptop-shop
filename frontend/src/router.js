import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/views/Home';
import Admin from '@/views/admin/Admin';
import Category from '@/views/admin/Category';
import Brand from '@/views/admin/Brand';
import Product from '@/views/admin/Product';

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      component: Home
    },
    {
      path: '/admin',
      component: Admin,
      children: [
        {
          path: 'category',
          component: Category
        },
        {
          path: 'brand',
          component: Brand
        },
        {
          path: 'product',
          component: Product
        }
      ]
    }
  ]
});
