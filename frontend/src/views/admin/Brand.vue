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

    <v-data-table :headers="headers" :items="brands" :loading="isLoading">
      <template v-slot:items="props">
        <td>{{ props.index + 1 }}</td>
        <td>{{ props.item.name }}</td>
        <td class="justify-center layout px-0">
          <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
          <v-icon small @click="deleteItem(props.item)">delete</v-icon>
        </td>
      </template>
    </v-data-table>

    <notification :isShow="showMessage" :message="crudMessage" :isSuccess="isCrudSuccess"/>
  </div>
</template>

<script>
import Notification from "@/components/Notification";
import { RepositoryFactory } from "@/repository/repositoryFactory";

const brandsRepository = RepositoryFactory.get("brands");

export default {
  components: {
    notification: Notification
  },
  data: () => ({
    form: false,
    headers: [
      { text: "No", value: "no" },
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
    },
    isLoading: false,
    showMessage: false,
    isCrudSuccess: false,
    crudMessage: ""
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
    async fecthAllData() {
      this.isLoading = true;
      const { data } = await brandsRepository.getAll();
      this.brands = data;
      this.isLoading = false;
    },

    editItem(item) {
      this.editedIndex = this.brands.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.form = true;
    },

    async deleteItem(item) {
      const index = this.brands.indexOf(item);

      if (confirm("Are you sure you want to delete this item?")) {
        let res = await brandsRepository.delete(item.id);
        if (res.status === 200) {
          this.brands.splice(index, 1);
          this.showNotification("Deleted successfully", true);
        } else {
          this.showNotification("Error when delete brand", false);
        }
      }
    },

    close() {
      this.form = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },

    async save() {
      if (this.editedIndex > -1) {
        this.updateBrand();
      } else {
        this.createBrand();
      }
      this.close();
    },

    async updateBrand() {
      let res = await brandsRepository.update({
        id: this.editedItem.id,
        name: this.editedItem.name
      });

      if (res.status === 200) {
        Object.assign(this.brands[this.editedIndex], res.data);
        this.showNotification("Updated successfully", true);
      } else {
        this.showNotification("Error when update brand", false);
      }
    },

    async createBrand() {
      let res = await brandsRepository.create({
        name: this.editedItem.name
      });

      if (res.status === 201) {
        this.brands.push(res.data);
        this.showNotification("Added successfully", true);
      } else {
        this.showNotification("Error when add brand", false);
      }
    },

    showNotification(message, isSuccess) {
      this.crudMessage = message;
      this.isCrudSuccess = isSuccess;
      this.showMessage = true;
    }
  }
};
</script>
