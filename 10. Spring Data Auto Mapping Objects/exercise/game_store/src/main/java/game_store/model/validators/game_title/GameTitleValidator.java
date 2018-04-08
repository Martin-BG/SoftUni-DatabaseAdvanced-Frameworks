package game_store.model.validators.game_title;

import game_store.constants.ValidationMessages;
import game_store.model.validators.ValidatorsUtil;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class GameTitleValidator implements ConstraintValidator<GameTitle, CharSequence> {

    private static final Pattern PATTERN_CAPITAL_FIRST = Pattern.compile("^(?=[A-Z]).*$");

    private int minLength;
    private int maxLength;
    private boolean nullable;
    private boolean capitalFirstLetter;

    @Override
    public void initialize(GameTitle gameTitle) {
        this.nullable = gameTitle.nullable();
        this.capitalFirstLetter = gameTitle.capitalFirstLetter();
        this.minLength = gameTitle.minLength();
        this.maxLength = gameTitle.maxLength();
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {

        if (value == null || value.length() == 0) {
            return this.nullable;
        }

        if (this.capitalFirstLetter && !PATTERN_CAPITAL_FIRST.matcher(value.toString()).matches()) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.GAME_TITLE_SHOULD_START_WITH_CAPITAL_LETTER);
            return false;
        }

        if (value.length() < this.minLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.GAME_TITLE_TOO_SHORT);
            return false;
        }

        if (value.length() > this.maxLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.GAME_TITLE_TOO_LONG);
            return false;
        }

        return true;
    }
}
