export default {
  methods: {
    handleException(err) {
      const data = err.response.data;
      const errMessage = data.message || data.errors[0];
      this.showNotification(errMessage, false);
    }
  }
};
