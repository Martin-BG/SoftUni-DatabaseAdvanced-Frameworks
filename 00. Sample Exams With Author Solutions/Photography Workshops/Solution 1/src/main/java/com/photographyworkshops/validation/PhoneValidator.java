package com.photographyworkshops.validation;

import com.photographyworkshops.validation.annotations.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone phone) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        if (phone != null) {
            Pattern phonePattern = Pattern.compile("^[+]\\d{1,3}\\/\\d{8,10}$");
            Matcher phoneMatcher = phonePattern.matcher(phone);
            if (!phoneMatcher.find()) {
                isValid = false;
            }
        } else {
            isValid = true;
        }

        return isValid;
    }
}
