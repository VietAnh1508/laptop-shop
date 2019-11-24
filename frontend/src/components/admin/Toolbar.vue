<template>
  <v-app-bar app flat>
    <v-app-bar-nav-icon
      class="ml-1"
      @click.stop="$emit('toggle-drawer')"
    ></v-app-bar-nav-icon>

    <v-toolbar-title>
      <span class="font-weight-light mr-1">Admin</span>
      <span>management</span>
    </v-toolbar-title>

    <v-spacer />

    <v-btn text color="grey">
      <span class="mr-1">Sign out</span>
      <v-icon grey>exit_to_app</v-icon>
    </v-btn>
  </v-app-bar>
</template>
