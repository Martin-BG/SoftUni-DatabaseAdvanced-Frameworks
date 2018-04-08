package game_store.model.validators.email;

import game_store.constants.ValidationConstrains;
import game_store.constants.ValidationMessages;
import game_store.model.validators.ValidatorsUtil;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<Email, CharSequence> {

    private int minLength;
    private int maxLength;
    private Pattern pattern;
    private boolean nullable;

    @Override
    public void initialize(final Email email) {
        this.minLength = email.minLength();
        this.maxLength = email.maxLength();
        this.pattern = Pattern.compile(ValidationConstrains.USER_EMAIL_REGEX);
        this.nullable = email.nullable();
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {
        if (value == null) {
            return this.nullable;
        }

        String email = value.toString();
        int emailLength = email.length();

        if (emailLength < this.minLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.EMAIL_TOO_SHORT);
            return false;
        }

        if (emailLength > this.maxLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.EMAIL_TOO_LONG);
            return false;
        }

        if (!this.pattern.matcher(email).matches()) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.EMAIL_INVALID);
            return false;
        }

        return true;
    }
}
