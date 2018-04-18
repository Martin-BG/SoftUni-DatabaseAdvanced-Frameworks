package product_shop.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product_shop.model.entity.Product;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(final @NotNull BigDecimal low, final @NotNull BigDecimal high);
}
