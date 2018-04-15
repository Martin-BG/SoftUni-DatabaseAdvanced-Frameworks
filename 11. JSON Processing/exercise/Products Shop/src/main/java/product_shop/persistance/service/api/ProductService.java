package product_shop.persistance.service.api;

import product_shop.model.dto.binding.ProductDto;
import product_shop.model.dto.view.ProductNamePriceSellerNameDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void saveAll(ProductDto[] productsDto);

    List<ProductNamePriceSellerNameDto> getAvailableProductsInPriceRange(BigDecimal low, BigDecimal high);
}
