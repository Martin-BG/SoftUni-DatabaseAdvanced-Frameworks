package game_store.model.validators;

import javax.validation.ConstraintValidatorContext;

public final class ValidatorsUtil {

    private ValidatorsUtil() {
    }

    public static void setErrorMessage(final ConstraintValidatorContext context, final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }
}
