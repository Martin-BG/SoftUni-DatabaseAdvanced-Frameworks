package game_store.model.validators.game_trailer;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class GameTrailerValidator implements ConstraintValidator<GameTrailer, CharSequence> {

    private int minLength;
    private int maxLength;
    private boolean nullable;
    private Pattern pattern;

    @Override
    public void initialize(GameTrailer gameTrailer) {
        this.nullable = gameTrailer.nullable();
        this.minLength = gameTrailer.minLength();
        this.maxLength = gameTrailer.maxLength();
        this.pattern = Pattern.compile(gameTrailer.regex());
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {

        if (value == null || value.length() == 0) {
            return this.nullable;
        }

        return value.length() >= this.minLength &&
                value.length() <= this.maxLength &&
                this.pattern.matcher(value.toString()).matches();
    }
}
