package game_store.model.validators.user_name;

import game_store.constants.ValidationMessages;
import game_store.model.validators.ValidatorsUtil;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UserNameValidator implements ConstraintValidator<UserName, CharSequence> {

    private int minLength;
    private int maxLength;

    @Override
    public void initialize(UserName userName) {
        this.minLength = userName.minLength();
        this.maxLength = userName.maxLength();
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        if (value.length() < this.minLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.USER_NAME_TOO_SHORT);
            return false;
        }

        if (value.length() > this.maxLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.USER_NAME_TOO_LONG);
            return false;
        }

        return true;
    }
}
