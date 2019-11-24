<template>
  <v-app-bar flat color="#fed700">
    <v-toolbar-title>Logo</v-toolbar-title>
    <v-text-field
      hide-details
      prepend-icon="search"
      single-line
      placeholder="Bạn tìm gì..."
    ></v-text-field>
    <v-toolbar-items>
      <v-btn text small>Điện thoại</v-btn>
      <v-btn text small>Tablet</v-btn>
      <v-btn text small>Laptop</v-btn>
      <v-btn text small>Phụ kiện</v-btn>
      <v-btn text small>Đồng hồ</v-btn>
      <v-btn text small>
        Máy cũ
        <br />giá rẻ
      </v-btn>
      <v-btn text small>
        Tin
        <br />công nghệ
      </v-btn>
      <v-btn text small>Hỏi đáp</v-btn>
      <v-divider vertical></v-divider>
      <v-btn text small class="text-none">
        Sim số
        <br />Thẻ cào
      </v-btn>
      <v-divider vertical></v-divider>
      <v-btn text small class="text-none">
        Đóng tiền điện,
        <br />nước, trả góp
      </v-btn>
    </v-toolbar-items>
  </v-app-bar>
</template>

<style scoped>
.v-btn {
  min-width: 0;
}
</style>
