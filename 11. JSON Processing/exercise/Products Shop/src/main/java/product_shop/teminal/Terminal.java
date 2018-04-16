package product_shop.teminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import product_shop.model.dto.binding.CategoryDto;
import product_shop.model.dto.binding.ProductDto;
import product_shop.model.dto.binding.UserDto;
import product_shop.persistance.service.impl.CategoryServiceImpl;
import product_shop.persistance.service.impl.ProductServiceImpl;
import product_shop.persistance.service.impl.UserServiceImpl;
import product_shop.utils.JsonParser;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Controller
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/";
    private static final String SEED_USERS_JSON = RESOURCES_PATH + "seed/users.json";
    private static final String SEED_CATEGORIES_JSON = RESOURCES_PATH + "seed/categories.json";
    private static final String SEED_PRODUCTS_JSON = RESOURCES_PATH + "seed/products.json";
    private static final String OUTPUT_PRODUCTS_IN_RANGE_JSON = RESOURCES_PATH + "output/products-in-range.json";
    private static final String OUTPUT_USERS_SOLD_PRODUCTS_JSON = RESOURCES_PATH + "output/users-sold-products.json";

    private final JsonParser jsonParser;
    private final CategoryServiceImpl categoryService;
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;

    @Autowired
    public Terminal(final JsonParser jsonParser,
                    final CategoryServiceImpl categoryService,
                    final ProductServiceImpl productService,
                    final UserServiceImpl userService) {

        this.jsonParser = jsonParser;
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void run(final String... args) {
//        seedDatabase();

//        saveAvailableProductsInPriceRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

//        saveSuccessfulSellers();
    }

    private void seedDatabase() {
        UserDto[] usersDto = this.jsonParser.objectFromFile(UserDto[].class, SEED_USERS_JSON);
        this.userService.saveAll(usersDto);

        CategoryDto[] categoriesDto = this.jsonParser.objectFromFile(CategoryDto[].class, SEED_CATEGORIES_JSON);
        this.categoryService.saveAll(categoriesDto);

        ProductDto[] productsDto = this.jsonParser.objectFromFile(ProductDto[].class, SEED_PRODUCTS_JSON);
        this.productService.saveAll(productsDto);
    }

    private void saveAvailableProductsInPriceRange(BigDecimal low, BigDecimal high) {
        this.jsonParser.objectToFile(
                this.productService.getAvailableProductsInPriceRange(low, high),
                OUTPUT_PRODUCTS_IN_RANGE_JSON);

    }

    private void saveSuccessfulSellers() {
        this.jsonParser.objectToFile(
                this.userService.getSuccessfulSellers(),
                OUTPUT_USERS_SOLD_PRODUCTS_JSON);
    }
}
