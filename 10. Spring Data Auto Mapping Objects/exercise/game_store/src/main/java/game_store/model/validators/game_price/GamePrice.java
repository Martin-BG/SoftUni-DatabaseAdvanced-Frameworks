package game_store.model.validators.game_price;

import game_store.constants.ValidationConstrains;
import game_store.constants.ValidationMessages;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = GamePriceValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GamePrice {

    String message() default ValidationMessages.GAME_PRICE_CANNOT_BE_EMPTY;

    int precision() default ValidationConstrains.GAME_PRICE_MAX_PRECISION;

    boolean nullable() default ValidationConstrains.GAME_PRICE_CAN_BE_OMITTED;

    boolean negative() default ValidationConstrains.GAME_PRICE_CAN_BE_NEGATIVE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}