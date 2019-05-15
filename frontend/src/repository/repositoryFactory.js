import BrandsRepository from './brandsRepository';

const repository = {
  brands: BrandsRepository
};

export const RepositoryFactory = {
  get: name => repository[name]
};
