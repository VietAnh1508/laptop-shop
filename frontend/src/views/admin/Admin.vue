<template>
  <div>
    <navbar/>
    <v-content class="mx-4 my-3">
      <router-view/>
    </v-content>
  </div>
</template>

<script>
import Navbar from "@/components/admin/Navbar";

export default {
  components: {
    navbar: Navbar
  }
};
</script>
