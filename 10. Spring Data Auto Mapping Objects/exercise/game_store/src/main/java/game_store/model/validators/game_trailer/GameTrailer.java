package game_store.model.validators.game_trailer;

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
@Constraint(validatedBy = GameTrailerValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GameTrailer {

    String message() default ValidationMessages.GAME_INVALID_TRAILER_ID;

    int minLength() default ValidationConstrains.GAME_TRAILER_MIN_LENGTH;

    int maxLength() default ValidationConstrains.GAME_TRAILER_MAX_LENGTH;

    String regex() default ValidationConstrains.GAME_TRAILER_REGEX;

    boolean nullable() default ValidationConstrains.GAME_TRAILER_CAN_BE_OMITTED;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}