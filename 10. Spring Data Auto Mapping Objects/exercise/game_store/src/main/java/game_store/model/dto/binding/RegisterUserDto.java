package game_store.model.dto.binding;

import game_store.model.validators.email.Email;
import game_store.model.validators.password.Password;
import game_store.model.validators.password_matches.PasswordMatches;
import game_store.model.validators.user_name.UserName;

@PasswordMatches
public class RegisterUserDto {

    @Email
    private String email;

    @Password
    private String password;

    @Password
    private String confirmPassword;

    @UserName
    private String fullName;

    public RegisterUserDto() {
    }

    public RegisterUserDto(final String email, final String password, final String confirmPassword, final String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
}
