package game_store.model.utils;

import game_store.exceptions.InvalidCommandException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public final class ObjectValidator {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    private ObjectValidator() {
    }

    public static <T> void validate(T object) {
        Set<ConstraintViolation<T>> constraints = VALIDATOR.validate(object);

        if (!constraints.isEmpty()) {
            final StringBuilder sb = new StringBuilder();

            constraints.forEach(c -> sb.append(c.getMessage()).append(System.lineSeparator()));

            throw new InvalidCommandException(sb.toString().trim());
        }
    }
}
