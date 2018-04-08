package game_store.model.validators.thumbnail_url;

import game_store.constants.ValidationMessages;
import game_store.model.validators.ValidatorsUtil;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class ThumbnailValidator implements ConstraintValidator<ThumbnailUrl, CharSequence> {

    private boolean nullable;
    private int maxLength;
    private Pattern pattern;

    @Override
    public void initialize(final ThumbnailUrl thumbnailUrl) {
        this.nullable = thumbnailUrl.nullable();
        this.maxLength = thumbnailUrl.maxLength();
        this.pattern = Pattern.compile(thumbnailUrl.regex());
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {
        if (value == null) {
            return this.nullable;
        }

        if (value.length() > this.maxLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.GAME_THUMBNAIL_URL_TOO_LONG);
            return false;
        }

        return this.pattern.matcher(value.toString()).matches();
    }
}
