package game_store.model.validators.password_matches;

import game_store.model.dto.binding.RegisterUserDto;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches passwordMatches) {
    }

    @Override
    public boolean isValid(final Object o, final ConstraintValidatorContext context) {
        if (o instanceof RegisterUserDto) {
            RegisterUserDto user = (RegisterUserDto) o;
            return user.getPassword() != null && user.getPassword().equals(user.getConfirmPassword());
        }

        return false;
    }
}
