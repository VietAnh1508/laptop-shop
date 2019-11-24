<template>
  <v-navigation-drawer v-model="drawer" app class="grey darken-3">
    <v-layout column align-center>
      <v-flex class="mt-5">
        <v-avatar size="100">
          <v-img src="/avatar-1.png" />
        </v-avatar>
        <p class="white--text subheading mt-1">Viet Anh</p>
      </v-flex>
    </v-layout>
    <v-list>
      <v-list-item
        v-for="link in links"
        :key="link.text"
        router
        :to="link.route"
      >
        <v-list-item-action>
          <v-icon class="white--text">{{ link.icon }}</v-icon>
        </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title class="white--text">
            {{ link.text }}
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
export default {
  data() {
    return {
      drawer: true,
      links: [
        { icon: 'dashboard', text: 'Category', route: '/admin/category' },
        { icon: 'branding_watermark', text: 'Brand', route: '/admin/brand' },
        { icon: 'laptop', text: 'Product', route: '/admin/product' }
      ]
    };
  }
};
</script>
