package user.annotations;

import javax.validation.ConstraintValidatorContext;

public final class AnnotationsUtil {

    private AnnotationsUtil() {
    }

    public static void setErrorMessage(final ConstraintValidatorContext context, final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }
}
