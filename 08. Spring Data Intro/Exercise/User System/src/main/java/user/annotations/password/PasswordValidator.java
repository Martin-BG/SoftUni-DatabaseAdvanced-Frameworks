package user.annotations.password;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

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
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null || charSequence.length() < this.minLength || charSequence.length() > this.maxLength) {
            return false;
        }

        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean specialSymbol = false;

        if (this.hasUpper) {
            for (int i = 0; i < charSequence.length(); i++) {
                if (Character.isUpperCase(charSequence.charAt(i))) {
                    upper = true;
                    break;
                }
            }
        } else {
            upper = true;
        }

        if (this.hasLower) {
            for (int i = 0; i < charSequence.length(); i++) {
                if (Character.isLowerCase(charSequence.charAt(i))) {
                    lower = true;
                    break;
                }
            }
        } else {
            lower = true;
        }

        if (this.hasDigit) {
            for (int i = 0; i < charSequence.length(); i++) {
                if (Character.isDigit(charSequence.charAt(i))) {
                    digit = true;
                    break;
                }
            }
        } else {
            digit = true;
        }

        if (this.hasSpecialSymbol) {
            List<Character> specialSymbols = new ArrayList<>();
            Collections.addAll(specialSymbols, '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '<', '>', '?');
            for (int i = 0; i < charSequence.length(); i++) {
                if (specialSymbols.contains(charSequence.charAt(i))) {
                    specialSymbol = true;
                    break;
                }
            }
        } else {
            specialSymbol = true;
        }

        return upper && lower && digit && specialSymbol;
    }
}