package game_store.model.validators.game_description;

import game_store.constants.ValidationMessages;
import game_store.model.validators.ValidatorsUtil;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class GameDescriptionValidator implements ConstraintValidator<GameDescription, CharSequence> {

    private int minLength;
    private int maxLength;
    private boolean nullable;

    @Override
    public void initialize(GameDescription gameDescription) {
        this.nullable = gameDescription.nullable();
        this.minLength = gameDescription.minLength();
        this.maxLength = gameDescription.maxLength();
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {

        if (value == null || value.length() == 0) {
            return this.nullable;
        }

        if (value.length() < this.minLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.GAME_DESCRIPTION_TOO_SHORT);
            return false;
        }

        if (value.length() > this.maxLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.GAME_DESCRIPTION_TOO_LONG);
            return false;
        }

        return true;
    }
}
