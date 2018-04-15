package product_shop.persistance.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_shop.model.dto.binding.CategoryDto;
import product_shop.model.entity.Category;
import product_shop.persistance.repository.CategoryRepository;
import product_shop.persistance.service.api.CategoryService;

import javax.transaction.Transactional;
import java.util.Arrays;

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
}
