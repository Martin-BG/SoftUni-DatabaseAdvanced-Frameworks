package game_store.model.validators.email;

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
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default ValidationMessages.EMAIL_CANNOT_BE_EMPTY;

    int minLength() default ValidationConstrains.USER_EMAIL_MIN_LENGTH;

    int maxLength() default ValidationConstrains.USER_EMAIL_MAX_LENGTH;

    String regex() default ValidationConstrains.USER_EMAIL_REGEX;

    boolean nullable() default ValidationConstrains.USER_EMAIL_CAN_BE_EMPTY;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
