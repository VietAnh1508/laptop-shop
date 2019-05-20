<template>
  <div>
    <v-toolbar flat color="white">
      <v-toolbar-title>Brand</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn color="primary" dark class="mb-2" @click.stop="isShowForm = true">New Item</v-btn>
      <Modal
        v-model="isShowForm"
        :title="formTitle"
        :editedItem="editedItem"
        @closeModal="close"
        @saveItem="save"
      />
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

    <Notification v-model="showMessage" :message="crudMessage" :isSuccess="isCrudSuccess"/>
    <Confirm ref="confirm"/>
  </div>
</template>

<script>
import Modal from "@/components/Modal";
import Notification from "@/components/Notification";
import Confirm from "@/components/Confirm";
import { RepositoryFactory } from "@/repository/repositoryFactory";

const brandsRepository = RepositoryFactory.get("brands");

export default {
  components: {
    Modal,
    Notification,
    Confirm
  },
  data: () => ({
    headers: [
      { text: "No", value: "no" },
      { text: "Brand", value: "name" },
      { text: "Actions", align: "center", value: "name", sortable: false }
    ],
    brands: [],
    isShowForm: false,
    editedIndex: -1,
    editedItem: {
      id: "",
      name: ""
    },
    defaultItem: {
      id: "",
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
      this.isShowForm = true;
    },

    close() {
      this.isShowForm = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },

    save(item) {
      if (this.editedIndex > -1) {
        this.updateBrand(item);
      } else {
        this.createBrand(item);
      }
      this.close();
    },

    async updateBrand(item) {
      try {
        let res = await brandsRepository.update(item.id, {
          name: item.name
        });

        if (res.status === 200) {
          Object.assign(this.brands[this.editedIndex], res.data);
          this.showNotification("Updated successfully", true);
        }
      } catch (err) {
        let errMessage = err.response.data.errors[0];
        this.showNotification(errMessage, false);
      }
    },

    async createBrand(item) {
      try {
        let res = await brandsRepository.create({
          name: item.name
        });

        if (res.status === 201) {
          this.brands.push(res.data);
          this.showNotification("Added successfully", true);
        }
      } catch (err) {
        let errMessage = err.response.data.errors[0];
        this.showNotification(errMessage, false);
      }
    },

    async deleteItem(item) {
      const index = this.brands.indexOf(item);
      try {
        if (
          await this.$refs.confirm.open("Delete", "Are you sure?", {
            color: "red"
          })
        ) {
          let res = await brandsRepository.delete(item.id);
          if (res.status === 200) {
            this.brands.splice(index, 1);
            this.showNotification("Deleted successfully", true);
          }
        }
      } catch (err) {
        this.showNotification("Error when delete brand", false);
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
