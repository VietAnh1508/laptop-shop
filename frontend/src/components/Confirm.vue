<template>
  <v-dialog v-model="dialog" :max-width="options.width" :style="{ zIndex: options.zIndex }">
    <v-card>
      <v-toolbar dark :color="options.color" dense flat>
        <v-toolbar-title class="white--text">{{ title }}</v-toolbar-title>
      </v-toolbar>
      <v-card-text v-show="!!message">{{ message }}</v-card-text>
      <v-card-actions class="pt-0">
        <v-spacer></v-spacer>
        <v-btn color="grey" flat @click="cancel">Cancel</v-btn>
        <v-btn color="primary darken-1" flat @click="agree">Yes</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
    title: null,
    message: null,
    options: {
      color: "primary",
      width: 300,
      zIndex: 200
    },
    resolve: null,
    reject: null
  }),
  methods: {
    open(title, message, options) {
      this.title = title;
      this.message = message;
      this.options = Object.assign(this.options, options);
      this.dialog = true;
      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
    agree() {
      this.resolve(true);
      this.dialog = false;
    },
    cancel() {
      this.resolve(false);
      this.dialog = false;
    }
  }
};
</script>
