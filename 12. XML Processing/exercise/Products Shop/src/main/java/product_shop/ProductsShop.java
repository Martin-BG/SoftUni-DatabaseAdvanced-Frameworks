package product_shop;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import product_shop.utils.ModelMapperConfig;

@SpringBootApplication
public class ProductsShop {

    public static void main(final String[] args) {
        SpringApplication.run(ProductsShop.class, args);
    }

    @Bean
    public ModelMapper getMapper() {
        ModelMapper mapper = new ModelMapper();
        ModelMapperConfig config = new ModelMapperConfig(mapper);
        return mapper;
    }
}
