package product_shop.persistance.service.api;

import product_shop.model.dto.binding.CategoryDto;
import product_shop.model.entity.Category;

public interface CategoryService {

    void saveAll(CategoryDto[] categoriesDto);

    Category getRandom();
}
