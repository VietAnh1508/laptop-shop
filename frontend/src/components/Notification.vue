<template>
  <v-snackbar v-model="isShow" :color="color" bottom right :timeout="notificationTimeout">
    {{ message }}
    <v-btn flat @click="isShow = false">Close</v-btn>
  </v-snackbar>
</template>

<script>
export default {
  props: {
    isShow: {
      type: Boolean,
      required: true
    },
    message: {
      type: String,
      required: true
    },
    isSuccess: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      notificationTimeout: 6000
    };
  },
  computed: {
    color() {
      return this.isSuccess ? "success" : "error";
    }
  }
};
</script>
