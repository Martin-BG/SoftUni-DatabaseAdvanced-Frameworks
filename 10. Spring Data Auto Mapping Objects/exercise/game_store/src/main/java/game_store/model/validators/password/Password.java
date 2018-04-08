package game_store.model.validators.password;

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
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default ValidationMessages.PASSWORD_TOO_SIMPLE;

    int minLength() default ValidationConstrains.USER_PASSWORD_MIN_LENGTH;

    int maxLength() default ValidationConstrains.USER_PASSWORD_MAX_LENGTH;

    boolean containsDigit() default ValidationConstrains.USER_PASSWORD_SHOULD_CONTAIN_DIGIT;

    boolean containsLowerCase() default ValidationConstrains.USER_PASSWORD_SHOULD_CONTAIN_LOWER_CASE;

    boolean containsUpperCase() default ValidationConstrains.USER_PASSWORD_SHOULD_CONTAIN_UPPER_CASE;

    boolean nullable() default ValidationConstrains.USER_PASSWORD_CAN_BE_OMITTED;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}