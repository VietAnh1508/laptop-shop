<template>
  <div>
    <DataTableToolbar title="Category" buttonLabel="New item" @showForm="isShowForm = true"/>

    <Modal v-model="isShowForm" :title="formTitle" @closeModal="close" @saveItem="save">
      <v-text-field v-model="editedItem.name" label="Name"></v-text-field>
    </Modal>

    <v-data-table :headers="headers" :items="categories" :expand="false" :loading="isLoading">
      <template v-slot:items="props">
        <tr @click="expandRow(props)">
          <td>{{ props.index + 1 }}</td>
          <td>{{ props.item.name }}</td>
          <td class="justify-center layout px-0">
            <v-icon small class="mr-2" @click.stop="editItem(props.item, false)">edit</v-icon>
            <v-icon small @click.stop="deleteItem(props.item, false)">delete</v-icon>
          </td>
        </tr>
      </template>
      <template v-slot:expand="props">
        <v-card class="elevation-10">
          <v-card-title>
            <DataTableToolbar
              title="Child categories"
              buttonLabel="Add child category"
              @showForm="addChild(props.item)"
            />
          </v-card-title>
          <v-card-text>
            <v-data-table
              :headers="headers"
              :items="childCategories"
              hide-actions
              class="elevation-5"
            >
              <template v-slot:items="props">
                <td>{{ props.index + 1 }}</td>
                <td>{{ props.item.name }}</td>
                <td class="justify-center layout px-0">
                  <v-icon small class="mr-2" @click="editItem(props.item, true)">edit</v-icon>
                  <v-icon small @click="deleteItem(props.item, true)">delete</v-icon>
                </td>
              </template>
            </v-data-table>
          </v-card-text>
        </v-card>
      </template>
    </v-data-table>

    <Notification v-model="showMessage" :message="crudMessage" :isSuccess="isCrudSuccess"/>

    <Confirm ref="confirm"/>
  </div>
</template>

<script>
import DataTableToolbar from "@/components/DataTableToolbar";
import Modal from "@/components/Modal";
import Notification from "@/components/Notification";
import Confirm from "@/components/Confirm";

import { RepositoryFactory } from "@/repository/repositoryFactory";
const categoriesRepository = RepositoryFactory.get("categories");

import exceptionHandleMixin from "@/mixins/exceptionHandleMixin";

export default {
  components: {
    DataTableToolbar,
    Modal,
    Notification,
    Confirm
  },

  mixins: [exceptionHandleMixin],

  data: () => ({
    headers: [
      { text: "No", value: "no", sortable: false },
      { text: "Category", value: "name" },
      { text: "Actions", align: "center", value: "name", sortable: false }
    ],
    categories: [],
    childCategories: [],
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
    crudMessage: "",
    isAddChild: false,
    isEditChild: false
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "New category" : "Edit category";
    }
  },

  created() {
    this.fecthAllData();
  },

  methods: {
    async fecthAllData() {
      this.isLoading = true;
      const { data } = await categoriesRepository.getParentCategories();
      this.categories = data;
      this.isLoading = false;
    },

    async expandRow(props) {
      if (!props.expanded) {
        this.isLoading = true;
        let { data } = await categoriesRepository.getChildrenCategories(
          props.item.id
        );
        this.childCategories = data;
        this.isLoading = false;
      }

      props.expanded = !props.expanded;
    },

    editItem(item, isEditChild) {
      this.editedIndex = isEditChild
        ? this.childCategories.indexOf(item)
        : this.categories.indexOf(item);
      this.isEditChild = isEditChild;
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

    save() {
      if (this.editedIndex == -1) {
        if (this.isAddChild) {
          this.createChildCategory();
          this.isAddChild = false;
        } else {
          this.createCategory();
        }
      } else {
        if (this.isEditChild) {
          this.updateChildCategory();
          this.isEditChild = false;
        } else {
          this.updateCategory();
        }
      }

      this.close();
    },

    async updateCategory() {
      try {
        let res = await categoriesRepository.update(this.editedItem.id, {
          name: this.editedItem.name
        });

        if (res.status === 200) {
          Object.assign(this.categories[this.editedIndex], res.data);
          this.showNotification("Updated successfully", true);
        }
      } catch (err) {
        this.handleException(err);
      }
    },

    async updateChildCategory() {
      try {
        let res = await categoriesRepository.update(this.editedItem.id, {
          name: this.editedItem.name
        });

        if (res.status === 200) {
          Object.assign(this.childCategories[this.editedIndex], res.data);
          this.showNotification("Updated successfully", true);
        }
      } catch (err) {
        this.handleException(err);
      }
    },

    async createCategory() {
      try {
        let res = await categoriesRepository.create({
          name: this.editedItem.name
        });

        if (res.status === 201) {
          this.categories.push(res.data);
          this.showNotification("Added successfully", true);
        }
      } catch (err) {
        this.handleException(err);
      }
    },

    async createChildCategory() {
      try {
        let res = await categoriesRepository.create({
          name: this.editedItem.name,
          parentCategory: {
            id: this.editedItem.id
          }
        });

        if (res.status === 201) {
          this.childCategories.push(res.data);
          this.showNotification("Added successfully", true);
        }
      } catch (err) {
        this.handleException(err);
      }
    },

    async deleteItem(item, isDeleteChild) {
      const index = isDeleteChild
        ? this.childCategories.indexOf(item)
        : this.categories.indexOf(item);

      try {
        if (
          await this.$refs.confirm.open("Delete", "Are you sure?", {
            color: "red"
          })
        ) {
          let res = await categoriesRepository.delete(item.id);
          if (res.status === 204) {
            if (isDeleteChild) {
              this.childCategories.splice(index, 1);
            } else {
              this.categories.splice(index, 1);
            }
            this.showNotification("Deleted successfully", true);
          }
        }
      } catch (err) {
        this.showNotification("Error when delete category", false);
      }
    },

    addChild(item) {
      this.editedItem = Object.assign({}, item);
      this.editedItem.name = "";
      this.isAddChild = true;
      this.isShowForm = true;
    },

    showNotification(message, isSuccess) {
      this.crudMessage = message;
      this.isCrudSuccess = isSuccess;
      this.showMessage = true;
    }
  }
};
</script>
