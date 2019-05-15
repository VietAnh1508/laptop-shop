<template>
  <div>
    <v-toolbar flat color="white">
      <v-toolbar-title>Brand</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-dialog v-model="form" max-width="500px">
        <template v-slot:activator="{ on }">
          <v-btn color="primary" dark class="mb-2" v-on="on">New Item</v-btn>
        </template>

        <v-card>
          <v-card-title>
            <span class="title">{{ formTitle }}</span>
          </v-card-title>

          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12>
                  <v-text-field v-model="editedItem.name" label="Name"></v-text-field>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" flat @click="close">Cancel</v-btn>
            <v-btn color="blue darken-1" flat @click="save">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-toolbar>

    <v-data-table :headers="headers" :items="brands">
      <template v-slot:items="props">
        <td>{{ props.item.id }}</td>
        <td>{{ props.item.name }}</td>
        <td class="justify-center layout px-0">
          <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
          <v-icon small @click="deleteItem(props.item)">delete</v-icon>
        </td>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import { setTimeout } from "timers";
import axios from "axios";

export default {
  data: () => ({
    form: false,
    headers: [
      { text: "Id", value: "id" },
      { text: "Brand", value: "name" },
      { text: "Actions", align: "center", value: "name", sortable: false }
    ],
    brands: [],
    editedIndex: -1,
    editedItem: {
      name: ""
    },
    defaultItem: {
      name: ""
    }
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "New brand" : "Edit brand";
    }
  },

  created() {
    this.fecthAllData();
  },

  methods: {
    fecthAllData() {
      axios
        .get("/api/brands")
        .then(res => (this.brands = res.data))
        .catch(err => alert(err));
    },

    editItem(item) {
      this.editedIndex = this.brands.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.form = true;
    },

    deleteItem(item) {
      const index = this.brands.indexOf(item);

      confirm("Are you sure you want to delete this item?") &&
        axios
          .delete(`/api/brands/${item.id}`)
          .then(res => res.status === 200 && this.brands.splice(index, 1))
          .catch(err => console.log(err));
    },

    close() {
      this.form = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },

    save() {
      if (this.editedIndex > -1) {
        axios
          .put("/api/brands", {
            id: this.editedItem.id,
            name: this.editedItem.name
          })
          .then(
            res =>
              res.status === 200 &&
              Object.assign(this.brands[this.editedIndex], res.data)
          )
          .catch(err => console.log(err));
      } else {
        axios
          .post("/api/brands", {
            name: this.editedItem.name
          })
          .then(res => res.status === 201 && this.brands.push(res.data))
          .catch(err => console.log(err));
      }
      this.close();
    }
  }
};
</script>
