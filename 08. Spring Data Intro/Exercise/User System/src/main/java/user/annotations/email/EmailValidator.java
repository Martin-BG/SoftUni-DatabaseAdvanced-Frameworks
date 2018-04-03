package user.annotations.email;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<Email, CharSequence> {

    private int minUserNameLength;
    private int maxUserNameLength;
    private int maxHostNameLength;
    private Pattern pattern;

    @Override
    public void initialize(final Email email) {
        this.minUserNameLength = email.minUserNameLength();
        this.maxUserNameLength = email.maxUserNameLength();
        this.maxHostNameLength = email.maxHostNameLength();
        this.pattern = Pattern.compile(email.regex());
    }

    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        String email = value.toString();
        int userNameLength = email.indexOf("@");
        int hostNameLength = email.length() - email.lastIndexOf("@") - 1;

        if (userNameLength < this.minUserNameLength) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Email name length too short!")
                    .addConstraintViolation();
            return false;
        }

        if (userNameLength > this.maxUserNameLength) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Email name length too long!")
                    .addConstraintViolation();
            return false;
        }

        if (hostNameLength > this.maxHostNameLength) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Email host length too long!")
                    .addConstraintViolation();
            return false;
        }

        return this.pattern.matcher(email).matches();
    }
}
