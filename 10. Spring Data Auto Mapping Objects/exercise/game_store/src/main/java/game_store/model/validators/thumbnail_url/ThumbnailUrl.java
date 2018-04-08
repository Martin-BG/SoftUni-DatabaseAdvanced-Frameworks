package game_store.model.validators.thumbnail_url;

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
@Constraint(validatedBy = ThumbnailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ThumbnailUrl {

    String message() default ValidationMessages.GAME_THUMBNAIL_URL_INVALID;

    int maxLength() default ValidationConstrains.GAME_THUMBNAIL_URL_MAX_LENGTH;

    String regex() default ValidationConstrains.GAME_THUMBNAIL_URL_REGEX;

    boolean nullable() default ValidationConstrains.GAME_THUMBNAIL_URL_CAN_BE_OMITTED;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
