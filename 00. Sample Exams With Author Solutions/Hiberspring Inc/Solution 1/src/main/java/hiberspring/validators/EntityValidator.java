package hiberspring.validators;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public final class EntityValidator {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();;

    private EntityValidator() {
    }

    public static  <T> boolean isValid (T t) {
        Set<ConstraintViolation<T>> errors = validator.validate(t);
        return errors.size() == 0;
    }
}
