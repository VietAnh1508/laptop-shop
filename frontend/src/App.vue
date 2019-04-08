<template>
  <div id="app">
    <toolbar/>
    <router-view/>
  </div>
</template>

<script>
import Toolbar from "@/components/Toolbar";

export default {
  components: {
    toolbar: Toolbar
  }
};
</script>

<style>
</style>
