package product_shop.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import product_shop.model.dto.view.CategoryNameProductsCountAverageAndTotalPricesDto;
import product_shop.model.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM categories ORDER BY RAND () LIMIT 1", nativeQuery = true)
    Category getRandomEntity();

    @Query("SELECT new product_shop.model.dto.view.CategoryNameProductsCountAverageAndTotalPricesDto(" +
            "c.name, c.products.size, AVG(p.price), SUM(p.price)) " +
            "FROM Category AS c " +
            "JOIN c.products AS p " +
            "GROUP BY c.id " +
            "ORDER BY c.products.size DESC")
    List<CategoryNameProductsCountAverageAndTotalPricesDto> getCategoriesByProductsCount();
}
