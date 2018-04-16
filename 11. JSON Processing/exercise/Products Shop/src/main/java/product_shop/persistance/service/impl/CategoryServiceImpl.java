package product_shop.persistance.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_shop.model.dto.binding.CategoryDto;
import product_shop.model.dto.view.CategoryNameProductsCountAverageAndTotalPricesDto;
import product_shop.model.entity.Category;
import product_shop.persistance.repository.CategoryRepository;
import product_shop.persistance.service.api.CategoryService;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(final CategoryRepository categoryRepository,
                               final ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAll(final CategoryDto[] categoriesDto) {
        Category[] categories = this.modelMapper.map(categoriesDto, Category[].class);
        this.categoryRepository.saveAll(Arrays.asList(categories));
    }

    @Override
    public Category getRandom() {
        return this.categoryRepository.getRandomEntity();
    }

    public List<CategoryNameProductsCountAverageAndTotalPricesDto> getCategoriesByProductsCount() {
        return this.categoryRepository.getCategoriesByProductsCount();
/*        return this.categoryRepository
                .findAll()
                .stream()
                .map(category -> {
                    final CategoryNameProductsCountAverageAndTotalPricesDto categoryDto =
                            this.modelMapper.map(category, CategoryNameProductsCountAverageAndTotalPricesDto.class);

                    categoryDto.setProductsCount(category.getProducts().size());

                    final BigDecimal totalRevenue = category.getProducts()
                            .stream()
                            .map(Product::getPrice)
                            .reduce(BigDecimal::add)
                            .orElse(BigDecimal.ZERO);

                    categoryDto.setTotalRevenue(totalRevenue);

                    categoryDto.setAveragePrice(totalRevenue.doubleValue() / categoryDto.getProductsCount());

                    return categoryDto;
                })
                .sorted(Comparator.comparing(CategoryNameProductsCountAverageAndTotalPricesDto::getProductsCount).reversed())
                .collect(Collectors.toList());*/
    }
}
