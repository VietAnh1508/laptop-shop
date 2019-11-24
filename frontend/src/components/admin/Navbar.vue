<template>
  <nav>
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

    <v-app-bar flat>
      <v-app-bar-nav-icon
        class="grey--text"
        @click="drawer = !drawer"
      ></v-app-bar-nav-icon>
      <v-toolbar-title class="text-uppercase">
        <span class="font-weight-light mr-1">Admin</span>
        <span>management</span>
      </v-toolbar-title>

      <v-spacer />

      <v-btn text color="grey">
        <span class="mr-1">Sign out</span>
        <v-icon grey>exit_to_app</v-icon>
      </v-btn>
    </v-app-bar>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      drawer: null,
      links: [
        { icon: 'dashboard', text: 'Category', route: '/admin/category' },
        { icon: 'branding_watermark', text: 'Brand', route: '/admin/brand' },
        { icon: 'laptop', text: 'Product', route: '/admin/product' }
      ]
    };
  }
};
</script>
