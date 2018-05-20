package app.model.validation;

import javax.validation.Validation;
import javax.validation.Validator;

public final class ValidationUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ValidationUtil() {
    }

    public static <T> boolean isValid(T t) {
        return t != null && validator.validate(t).size() == 0;
    }

}
