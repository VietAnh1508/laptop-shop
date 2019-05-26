<template>
  <v-toolbar flat color="white">
    <v-toolbar-title>{{ title }}</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn color="primary" dark class="mb-2" @click.stop="showForm">{{ buttonLabel }}</v-btn>
  </v-toolbar>
</template>

<script>
export default {
  props: {
    title: {
      type: String
    },
    buttonLabel: {
      type: String
    }
  },
  methods: {
    showForm() {
      this.$emit("showForm");
    }
  }
};
</script>
