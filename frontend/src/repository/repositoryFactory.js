import BrandsRepository from './brandsRepository';
import CategoryRepository from './categoryRepository';

const repository = {
  brands: BrandsRepository,
  categories: CategoryRepository
};

export const RepositoryFactory = {
  get: name => repository[name]
};
