package user.annotations.password;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

    private static final Pattern PATTERN_LOWER = Pattern.compile("[a-z]");
    private static final Pattern PATTERN_UPPER = Pattern.compile("[A-Z]");
    private static final Pattern PATTERN_DIGIT = Pattern.compile("[0-9]");
    private static final Pattern PATTERN_SYMBOL = Pattern.compile("[!@#$%^&*()_+<>?]");

    private int minLength;
    private int maxLength;
    private boolean hasUpper;
    private boolean hasLower;
    private boolean hasDigit;
    private boolean hasSpecialSymbol;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.hasUpper = password.containsUpperCase();
        this.hasLower = password.containsLowerCase();
        this.hasDigit = password.containsDigit();
        this.hasSpecialSymbol = password.containsSpecialSymbols();
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {
        if (value == null) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Password cannot be null")
                    .addConstraintViolation();

            return false;
        }

        if (value.length() < this.minLength) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Password too short!")
                    .addConstraintViolation();

            return false;
        }

        if (value.length() > this.maxLength) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Password too long!")
                    .addConstraintViolation();

            return false;
        }

        String password = value.toString();

        if (!PATTERN_LOWER.matcher(password).find() && this.hasLower) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Password should contain lowercase letter!")
                    .addConstraintViolation();

            return false;
        }

        if (!PATTERN_UPPER.matcher(password).find() && this.hasUpper) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Password should contain uppercase letter!")
                    .addConstraintViolation();

            return false;
        }

        if (!PATTERN_DIGIT.matcher(password).find() && this.hasDigit) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Password should contain digit!")
                    .addConstraintViolation();

            return false;
        }

        if (!PATTERN_SYMBOL.matcher(password).find() && this.hasSpecialSymbol) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Password should contain one of: !@#$%^&*()_+<>?")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}