<template>
  <nav>
    <Toolbar @toggle-drawer="$refs.drawer.drawer = !$refs.drawer.drawer" />
    <Sidebar ref="drawer" />
  </nav>
</template>

<script>
import Toolbar from './Toolbar';
import Sidebar from './Sidebar';

export default {
  components: {
    Toolbar,
    Sidebar
  }
};
</script>
