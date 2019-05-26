import Repository from './repository';

const resource = '/categories';

export default {
  getAll() {
    return Repository.get(`${resource}`);
  },
  getParentCategories() {
    return Repository.get(`${resource}/parent`);
  },
  getChildrenCategories(parentId) {
    return Repository.get(`${resource}/children`, {
      params: {
        parentId
      }
    });
  },
  create(payload) {
    return Repository.post(`${resource}`, payload);
  },
  update(id, payload) {
    return Repository.put(`${resource}/${id}`, payload);
  },
  delete(id) {
    return Repository.delete(`${resource}/${id}`);
  }
};
