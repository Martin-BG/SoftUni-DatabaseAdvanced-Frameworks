package product_shop.persistance.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_shop.model.dto.binding.ProductDto;
import product_shop.model.dto.view.ProductNamePriceSellerNameDto;
import product_shop.model.entity.Category;
import product_shop.model.entity.Product;
import product_shop.model.entity.User;
import product_shop.persistance.repository.ProductRepository;
import product_shop.persistance.service.api.CategoryService;
import product_shop.persistance.service.api.ProductService;
import product_shop.persistance.service.api.UserService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository,
                              final ModelMapper modelMapper, final CategoryService categoryService, final UserService userService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void saveAll(final ProductDto[] productsDto) {
        Product[] products = this.modelMapper.map(productsDto, Product[].class);

        Random random = new Random();
        for (final Product product : products) {
            User user = this.getRandomUser();

            while (user == null) {
                user = this.getRandomUser();
            }

            product.setSeller(user);
            this.productRepository.saveAndFlush(product);
            user.getSell().add(product);

            if (random.nextInt(2) == 0) {
                user = this.getRandomUser();
                product.setBuyer(user);
                user.getBuy().add(product);
            }

            while (random.nextInt(3) != 0) {
                Category category = this.getRandomCategory();
                if (!category.getProducts().contains(product)) {
                    category.getProducts().add(product);
                    product.getCategories().add(category);
                }
            }
        }
    }

    @Override
    public List<ProductNamePriceSellerNameDto> getAvailableProductsInPriceRange(final BigDecimal low, final BigDecimal high) {
        return this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, high)
                .stream()
                .map(product -> this.modelMapper.map(product, ProductNamePriceSellerNameDto.class))
                .collect(Collectors.toList());
    }

    private Category getRandomCategory() {
        return this.categoryService.getRandom();
    }

    private User getRandomUser() {
        return this.userService.getRandom();
    }
}
