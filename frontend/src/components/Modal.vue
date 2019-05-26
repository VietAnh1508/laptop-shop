<template>
  <v-dialog v-model="isShow" max-width="500px">
    <v-card>
      <v-card-title>
        <span class="title">{{ title }}</span>
      </v-card-title>
      <v-form class="px-3">
        <v-card-text>
          <slot></slot>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="grey darken-1" flat @click="close">Cancel</v-btn>
          <v-btn color="blue darken-1" flat @click="save">Save</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: {
    title: {
      type: String
    },
    value: {
      type: Boolean
    }
  },
  computed: {
    isShow: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit("input", value);
      }
    },
    itemName: {
      get() {
        return this.editedItem.name;
      },
      set(value) {
        this.name = value;
      }
    }
  },
  methods: {
    close() {
      this.$emit("closeModal");
    },

    save() {
      this.$emit("saveItem");
    }
  }
};
</script>
