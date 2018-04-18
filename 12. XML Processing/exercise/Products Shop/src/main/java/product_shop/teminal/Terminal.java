package product_shop.teminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import product_shop.model.dto.binding.CategoryDto;
import product_shop.model.dto.binding.ProductDto;
import product_shop.model.dto.binding.UserDto;
import product_shop.model.dto.binding.xml.CategoriesCategoryDto;
import product_shop.model.dto.binding.xml.ProductsProductDto;
import product_shop.model.dto.binding.xml.UsersUserDto;
import product_shop.model.dto.view.ProductNamePriceSellerNameDto;
import product_shop.model.dto.view.UserFirstAndLastNamesAndSoldProductsDto;
import product_shop.model.dto.view.xml.ProductsProductNamePriceSellerNameDto;
import product_shop.model.dto.view.xml.UsersUserFirstAndLastNamesAndSoldProductsDto;
import product_shop.persistance.service.impl.CategoryServiceImpl;
import product_shop.persistance.service.impl.ProductServiceImpl;
import product_shop.persistance.service.impl.UserServiceImpl;
import product_shop.utils.JsonParser;
import product_shop.utils.XmlParser;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Controller
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/";
    private static final String SEED_USERS_JSON = RESOURCES_PATH + "json/in/users.json";
    private static final String SEED_CATEGORIES_JSON = RESOURCES_PATH + "json/in/categories.json";
    private static final String SEED_PRODUCTS_JSON = RESOURCES_PATH + "json/in/products.json";
    private static final String OUTPUT_PRODUCTS_IN_RANGE_JSON = RESOURCES_PATH + "json/out/products-in-range.json";
    private static final String OUTPUT_USERS_SOLD_PRODUCTS_JSON = RESOURCES_PATH + "json/out/users-sold-products.json";
    private static final String OUTPUT_CATEGORIES_BY_PRODUCTS_JSON = RESOURCES_PATH + "json/out/categories-by-products.json";
    private static final String OUTPUT_USERS_AND_PRODUCTS_JSON = RESOURCES_PATH + "json/out/users-and-products.json";
    private static final String XML_IN_USERS_XML = RESOURCES_PATH + "xml/in/users.xml";
    private static final String XML_IN_CATEGORIES_XML = RESOURCES_PATH + "xml/in/categories.xml";
    private static final String XML_IN_PRODUCTS_XML = RESOURCES_PATH + "xml/in/products.xml";
    private static final String XML_OUT_PRODUCTS_IN_RANGE_XML = RESOURCES_PATH + "xml/out/products-in-range.xml";
    private static final String XML_OUT_USERS_SOLD_PRODUCTS_XML = RESOURCES_PATH + "xml/out/users-sold-products.xml";

    private final JsonParser jsonParser;
    private final CategoryServiceImpl categoryService;
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;
    private final XmlParser xmlParser;

    @Autowired
    public Terminal(final JsonParser jsonParser,
                    final CategoryServiceImpl categoryService,
                    final ProductServiceImpl productService,
                    final UserServiceImpl userService,
                    final XmlParser xmlParser) {

        this.jsonParser = jsonParser;
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(final String... args) {
//        jsonSolution();

        xmlSolution();
    }

    private void xmlSolution() {
//        seedDatabaseXml();

        saveAvailableProductsInPriceRangeXml(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        saveSuccessfulSellersXml();
//
//        saveCategoriesByProductCountXml();
//
//        saveUsersAndSoldProductsXml();
    }

    private void saveSuccessfulSellersXml() {
        final List<UserFirstAndLastNamesAndSoldProductsDto> successfulSellers = this.userService.getSuccessfulSellers();
        UsersUserFirstAndLastNamesAndSoldProductsDto users = new UsersUserFirstAndLastNamesAndSoldProductsDto();
        users.setUsers(successfulSellers);
        this.xmlParser.objectToFile(
                users,
                XML_OUT_USERS_SOLD_PRODUCTS_XML);
    }

    private void saveAvailableProductsInPriceRangeXml(final BigDecimal low, final BigDecimal high) {
        final List<ProductNamePriceSellerNameDto> productsList = this.productService.getAvailableProductsInPriceRange(low, high);
        ProductsProductNamePriceSellerNameDto products = new ProductsProductNamePriceSellerNameDto();
        products.setProducts(productsList);
        this.xmlParser.objectToFile(
                products,
                XML_OUT_PRODUCTS_IN_RANGE_XML);
    }

    private void seedDatabaseXml() {
        UsersUserDto usersDto = this.xmlParser.objectFromFile(UsersUserDto.class, XML_IN_USERS_XML);
        this.userService.saveAll(usersDto.getUsers().toArray(new UserDto[0]));

        CategoriesCategoryDto categoriesDto = this.xmlParser.objectFromFile(CategoriesCategoryDto.class, XML_IN_CATEGORIES_XML);
        this.categoryService.saveAll(categoriesDto.getCategories().toArray(new CategoryDto[0]));

        ProductsProductDto productsDto = this.xmlParser.objectFromFile(ProductsProductDto.class, XML_IN_PRODUCTS_XML);
        this.productService.saveAll(productsDto.getProducts().toArray(new ProductDto[0]));
    }

    private void jsonSolution() {
        seedDatabaseJson();

        saveAvailableProductsInPriceRangeJson(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        saveSuccessfulSellersJson();

        saveCategoriesByProductCountJson();

        saveUsersAndSoldProductsJson();
    }

    private void seedDatabaseJson() {
        UserDto[] usersDto = this.jsonParser.objectFromFile(UserDto[].class, SEED_USERS_JSON);
        this.userService.saveAll(usersDto);

        CategoryDto[] categoriesDto = this.jsonParser.objectFromFile(CategoryDto[].class, SEED_CATEGORIES_JSON);
        this.categoryService.saveAll(categoriesDto);

        ProductDto[] productsDto = this.jsonParser.objectFromFile(ProductDto[].class, SEED_PRODUCTS_JSON);
        this.productService.saveAll(productsDto);
    }

    private void saveAvailableProductsInPriceRangeJson(BigDecimal low, BigDecimal high) {
        this.jsonParser.objectToFile(
                this.productService.getAvailableProductsInPriceRange(low, high),
                OUTPUT_PRODUCTS_IN_RANGE_JSON);

    }

    private void saveSuccessfulSellersJson() {
        this.jsonParser.objectToFile(
                this.userService.getSuccessfulSellers(),
                OUTPUT_USERS_SOLD_PRODUCTS_JSON);
    }

    private void saveCategoriesByProductCountJson() {
        this.jsonParser.objectToFile(
                this.categoryService.getCategoriesByProductsCount(),
                OUTPUT_CATEGORIES_BY_PRODUCTS_JSON);
    }

    private void saveUsersAndSoldProductsJson() {
        this.jsonParser.objectToFile(
                this.userService.getSellsByUser(),
                OUTPUT_USERS_AND_PRODUCTS_JSON);
    }
}
