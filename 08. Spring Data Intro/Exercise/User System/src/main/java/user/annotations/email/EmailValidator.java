package user.annotations.email;

import org.springframework.stereotype.Component;
import user.annotations.AnnotationsUtil;
import user.constants.TextConstants;

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
            AnnotationsUtil.setErrorMessage(context, TextConstants.EMAIL_NAME_LENGTH_TOO_SHORT);
            return false;
        }

        if (userNameLength > this.maxUserNameLength) {
            AnnotationsUtil.setErrorMessage(context, TextConstants.EMAIL_NAME_LENGTH_TOO_LONG);
            return false;
        }

        if (hostNameLength > this.maxHostNameLength) {
            AnnotationsUtil.setErrorMessage(context, TextConstants.EMAIL_HOST_LENGTH_TOO_LONG);
            return false;
        }

        return this.pattern.matcher(email).matches();
    }
}
