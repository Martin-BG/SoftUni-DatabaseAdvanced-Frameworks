package app.runners;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.shampoos.Shampoo;
import app.repositories.IngredientRepository;
import app.repositories.LabelRepository;
import app.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Consumer;

@Controller
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooRepository shampooRepository;
    private final LabelRepository labelRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public ConsoleRunner(final ShampooRepository shampooRepository,
                         final LabelRepository labelRepository,
                         final IngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.labelRepository = labelRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(final String... args) {
        this.shampooRepository
                .findAllBySizeOrderById(Size.MEDIUM)
                .forEach(printShampooDetails());
        System.out.println("============= 1. END findAllBySizeOrderById(Size.MEDIUM) =============");

        this.shampooRepository
                .findAllBySizeOrLabelOrderByPriceAsc(Size.MEDIUM, this.labelRepository.getOne(10L))
                .forEach(printShampooDetails());
        System.out.println("============= 2. END findAllBySizeOrLabelOrderByPriceAsc(Size.MEDIUM, Label ID 10) =============");

        this.shampooRepository
                .findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal.valueOf(5))
                .forEach(printShampooDetails());
        System.out.println("============= 3. END findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal.valueOf(5)) =============");

        this.ingredientRepository
                .findAllByNameIsStartingWith("M")
                .forEach(printIngredientDetails());
        System.out.println("============= 4. END findAllByNameIsStartingWith(\"M\") =============");

        this.ingredientRepository
                .findAllByNameInOrderByPriceAsc(Arrays.asList("Lavender", "Herbs", "Apple", "Invalid"))
                .forEach(printIngredientDetails());
        System.out.println("============= 5. END findAllByNameInOrderByPriceAsc(\"Lavender\", \"Herbs\", \"Apple\", \"Invalid\") =============");

        System.out.println(this.shampooRepository
                .countAllByPriceIsLessThan(BigDecimal.valueOf(8.5)));
        System.out.println("============= 6. END countAllByPriceIsLessThan(8.5) =============");

        this.shampooRepository
                .findByHavingIngredients(Arrays.asList(
                        this.ingredientRepository.getFirstByNameEquals("Berry"),
                        this.ingredientRepository.getFirstByNameEquals("Mineral-Colagen")))
                .forEach(printShampooDetails());
        System.out.println("============= 7. END findByHavingIngredients(Berry, Mineral-Colagen) =============");

        this.shampooRepository
                .findAllByIngredientsCountLess(2)
                .forEach(printShampooDetails());
        System.out.println("============= 8. END .findAllByIngredientsCountLess(2) =============");

        System.out.println(this.shampooRepository
                .getTotalIngredientsCostForShampoo("Silk Comb"));
        System.out.println("============= 9. END .getTotalIngredientsCostForShampoo(\"Silk Comb\") =============");
        System.out.println(this.shampooRepository
                .getTotalIngredientsCostForShampoo("Fresh it up!"));
        System.out.println("============= 9. END .getTotalIngredientsCostForShampoo(\"Fresh it up!\") =============");

        System.out.println(this.shampooRepository
                .getTotalIngredientsCostForShampooNamedQuery("Silk Comb"));
        System.out.println("============= 9. END .getTotalIngredientsCostForShampooNamedQuery(\"Silk Comb\") =============");
        System.out.println(this.shampooRepository
                .getTotalIngredientsCostForShampooNamedQuery("Fresh it up!"));
        System.out.println("============= 9. END .getTotalIngredientsCostForShampooNamedQuery(\"Fresh it up!\") =============");

        this.ingredientRepository
                .deleteIngredientByName("Active-Caffeine");
        System.out.println("============= 10. END .deleteIngredientsByName(\"Active-Caffeine\") =============");

        this.ingredientRepository
                .deleteIngredientByNameNamedQuery("Micro-Crystals");
        System.out.println("============= 10. END .deleteIngredientsByNameNamedQuery(\"Micro-Crystals\") =============");

        this.ingredientRepository
                .increaseAllIngredientsPriceBy10Percents();
        System.out.println("============= 11. END .increaseAllIngredientsPriceBy10Percents() =============");

        this.ingredientRepository
                .increaseAllIngredientsPriceBy10PercentsNamedQuery();
        System.out.println("============= 11. END .increaseAllIngredientsPriceBy10PercentsNamedQuery() =============");

        this.ingredientRepository
                .increaseIngredientsPriceBy10PercentsFromList(Arrays.asList("Lavender", "Herbs", "Apple", "Invalid"));
        System.out.println("============= 12. END .increaseIngredientsPriceBy10PercentsFromList(\"Lavender\", \"Herbs\", \"Apple\", \"Invalid\") =============");

        this.ingredientRepository
                .increaseIngredientsPriceBy10PercentsFromListNamedQuery(Arrays.asList("Lavender", "Herbs", "Apple", "Invalid"));
        System.out.println("============= 12. END .increaseIngredientsPriceBy10PercentsFromListNamedQuery(\"Lavender\", \"Herbs\", \"Apple\", \"Invalid\") =============");
    }

    private Consumer<Ingredient> printIngredientDetails() {
        return ingredient -> System.out.println(ingredient.getName());
    }

    private Consumer<Shampoo> printShampooDetails() {
        return shampoo -> System.out.printf("%3d %s %s %.2flv.%n",
                shampoo.getId(), shampoo.getBrand(), shampoo.getSize(), shampoo.getPrice());
    }
}
