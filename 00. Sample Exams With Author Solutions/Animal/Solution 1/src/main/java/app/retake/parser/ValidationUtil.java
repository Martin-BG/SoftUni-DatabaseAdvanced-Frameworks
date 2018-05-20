package app.retake.parser;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

public final class ValidationUtil {

    public static <T> boolean isValid(T t) {
        Set<ConstraintViolation<T>> errors = Validation
                .buildDefaultValidatorFactory()
                .getValidator()
                .validate(t);
        return errors.size() == 0;
    }
}
