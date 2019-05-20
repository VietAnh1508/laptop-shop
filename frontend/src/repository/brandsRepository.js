import Repository from './repository';

const resource = '/brands';

export default {
  getAll() {
    return Repository.get(`${resource}`);
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
