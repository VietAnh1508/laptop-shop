<template>
  <v-toolbar flat color="#fed700">
    <v-toolbar-title>Logo</v-toolbar-title>
    <v-text-field hide-details prepend-icon="search" single-line placeholder="Bạn tìm gì..."></v-text-field>
    <v-toolbar-items>
      <v-btn flat small>Điện thoại</v-btn>
      <v-btn flat small>Tablet</v-btn>
      <v-btn flat small>Laptop</v-btn>
      <v-btn flat small>Phụ kiện</v-btn>
      <v-btn flat small>Đồng hồ</v-btn>
      <v-btn flat small>
        Máy cũ
        <br>giá rẻ
      </v-btn>
      <v-btn flat small>
        Tin
        <br>công nghệ
      </v-btn>
      <v-btn flat small>Hỏi đáp</v-btn>
      <v-divider vertical></v-divider>
      <v-btn flat small class="text-none">
        Sim số
        <br>Thẻ cào
      </v-btn>
      <v-divider vertical></v-divider>
      <v-btn flat small class="text-none">
        Đóng tiền điện,
        <br>nước, trả góp
      </v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
export default {};
</script>

<style scoped>
.v-btn {
  min-width: 0;
}
</style>
