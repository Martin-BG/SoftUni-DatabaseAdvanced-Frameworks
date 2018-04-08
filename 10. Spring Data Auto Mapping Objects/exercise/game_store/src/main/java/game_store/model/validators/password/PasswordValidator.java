package game_store.model.validators.password;

import game_store.constants.ValidationMessages;
import game_store.model.validators.ValidatorsUtil;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

    private static final Pattern PATTERN_LOWER = Pattern.compile("[a-z]");
    private static final Pattern PATTERN_UPPER = Pattern.compile("[A-Z]");
    private static final Pattern PATTERN_DIGIT = Pattern.compile("[0-9]");

    private int minLength;
    private int maxLength;
    private boolean hasUpper;
    private boolean hasLower;
    private boolean hasDigit;
    private boolean nullable;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.hasUpper = password.containsUpperCase();
        this.hasLower = password.containsLowerCase();
        this.hasDigit = password.containsDigit();
        this.nullable = password.nullable();
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            if (this.nullable) {
                return true;
            }

            ValidatorsUtil.setErrorMessage(context, ValidationMessages.PASSWORD_CANNOT_BE_EMPTY);
            return false;
        }

        if (value.length() < this.minLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.PASSWORD_TOO_SHORT);
            return false;
        }

        if (value.length() > this.maxLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.PASSWORD_TOO_LONG);
            return false;
        }

        String password = value.toString();

        if (this.hasLower && !PATTERN_LOWER.matcher(password).find()) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER);
            return false;
        }

        if (this.hasUpper && !PATTERN_UPPER.matcher(password).find()) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER);
            return false;
        }

        if (this.hasDigit && !PATTERN_DIGIT.matcher(password).find()) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.PASSWORD_SHOULD_CONTAIN_DIGIT);
            return false;
        }

        return true;
    }
}
