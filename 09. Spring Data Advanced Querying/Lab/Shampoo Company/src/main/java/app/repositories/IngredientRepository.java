package app.repositories;

import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<BasicIngredient, Long> {

    List<Ingredient> findAllByNameIsStartingWith(final String name);

    List<Ingredient> findAllByNameInOrderByPriceAsc(final Collection<String> name);

    Ingredient getFirstByNameEquals(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM BasicIngredient AS b WHERE b.name = :name")
    void deleteIngredientByName(@Param("name") String ingredientName);

    @Modifying
    @Transactional
    void deleteIngredientByNameNamedQuery(@Param("name") String ingredientName);

    @Modifying
    @Transactional
    @Query("UPDATE BasicIngredient AS b SET b.price = b.price * 1.1")
    void increaseAllIngredientsPriceBy10Percents();

    @Modifying
    @Transactional
    void increaseAllIngredientsPriceBy10PercentsNamedQuery();


    @Modifying
    @Transactional
    @Query("UPDATE BasicIngredient AS b SET b.price = b.price * 1.1 WHERE b.name IN :names")
    void increaseIngredientsPriceBy10PercentsFromList(@Param("names") List<String> ingredientNames);

    @Modifying
    @Transactional
    void increaseIngredientsPriceBy10PercentsFromListNamedQuery(@Param("names") List<String> ingredientNames);

}
