package app.repositories;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.labels.BasicLabel;
import app.model.shampoos.BasicShampoo;
import app.model.shampoos.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<BasicShampoo, Long> {

    List<Shampoo> findAllBySizeOrderById(final Size size);

    List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(final Size size, final BasicLabel label);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(final BigDecimal price);

    long countAllByPriceIsLessThan(final BigDecimal price);

    @Query("SELECT s " +
            "FROM BasicShampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE i IN :ingredients")
    List<Shampoo> findByHavingIngredients(@Param("ingredients") List<Ingredient> ingredients);

    @Query("SELECT s " +
            "FROM BasicShampoo AS s " +
            "WHERE s.ingredients.size < :count")
    List<Shampoo> findAllByIngredientsCountLess(@Param("count") int count);

    @Query("SELECT sum(i.price) " +
            "FROM BasicShampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE s.brand = :brand")
    BigDecimal getTotalIngredientsCostForShampoo(@Param("brand") String shampooBrand);

    BigDecimal getTotalIngredientsCostForShampooNamedQuery(@Param("brand") String shampooBrand);
}
