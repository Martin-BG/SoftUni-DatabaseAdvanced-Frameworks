package org.softuni.mostwanted.parser;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public final class ValidationUtil {
    private final Validator validator;

    public ValidationUtil() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <T> boolean isValid(T t) {
        return t != null && this.validator.validate(t).isEmpty();
    }
}
