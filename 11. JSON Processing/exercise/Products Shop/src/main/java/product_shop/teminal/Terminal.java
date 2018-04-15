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

import java.math.BigDecimal;

@Controller
public class Terminal implements CommandLineRunner {

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
//
//        saveAvailableProductsInPriceRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        //saveSuccessfulSellers();
    }

    private void seedDatabase() {
        UserDto[] usersDto = this.jsonParser.objectFromFile(UserDto[].class, "/seed/users.json");
        this.userService.saveAll(usersDto);

        CategoryDto[] categoriesDto = this.jsonParser.objectFromFile(CategoryDto[].class, "/seed/categories.json");
        this.categoryService.saveAll(categoriesDto);

        ProductDto[] productsDto = this.jsonParser.objectFromFile(ProductDto[].class, "/seed/products.json");
        this.productService.saveAll(productsDto);
    }

    private void saveAvailableProductsInPriceRange(BigDecimal low, BigDecimal high) {
        this.jsonParser.objectToFile(
                this.productService.getAvailableProductsInPriceRange(low, high),
                "output/products-in-range.json");

    }

    private void saveSuccessfulSellers() {
        this.jsonParser.objectToFile(
                this.userService.GetSuccessfulSellers(),
                "output/users-sold-products.json");
    }
}
