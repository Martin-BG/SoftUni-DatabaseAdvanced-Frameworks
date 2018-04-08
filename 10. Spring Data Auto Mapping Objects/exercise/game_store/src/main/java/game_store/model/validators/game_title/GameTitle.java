package game_store.model.validators.game_title;

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
@Constraint(validatedBy = GameTitleValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GameTitle {

    String message() default ValidationMessages.GAME_TITLE_CANNOT_BE_EMPTY;

    int minLength() default ValidationConstrains.GAME_TITLE_MIN_LENGTH;

    int maxLength() default ValidationConstrains.GAME_TITLE_MAX_LENGTH;

    boolean capitalFirstLetter() default ValidationConstrains.GAME_TITLE_UPPER_FIRST_LETTER;

    boolean nullable() default ValidationConstrains.GAME_TITLE_CAN_BE_OMITTED;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}