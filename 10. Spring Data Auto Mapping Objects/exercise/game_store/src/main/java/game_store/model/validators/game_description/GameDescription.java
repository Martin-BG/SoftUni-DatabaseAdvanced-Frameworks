package game_store.model.validators.game_description;

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
@Constraint(validatedBy = GameDescriptionValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GameDescription {

    String message() default ValidationMessages.GAME_DESCRIPTION_CANNOT_BE_EMPTY;

    int minLength() default ValidationConstrains.GAME_DESC_MIN_LENGTH;

    int maxLength() default ValidationConstrains.GAME_DESC_MAX_LENGTH;

    boolean nullable() default ValidationConstrains.GAME_DESC_CAN_BE_OMITTED;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}