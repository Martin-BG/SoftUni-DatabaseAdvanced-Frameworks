package gamestoreapp.model.dto.binding;

public class UserRegisterBindingModel {

    private String a;
    private String password;
    private String confirmPassword;
    private String b;

    public UserRegisterBindingModel() {
    }

    public UserRegisterBindingModel(String email, String password, String confirmPassword, String fullName) {
        this.a = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.b = fullName;
    }

    public String getA() {
        return this.a;
    }

    public void setA(String email) {
        this.a = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getB() {
        return this.b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public boolean isPasswordMatch() {
        return this.password.trim().equals(this.confirmPassword.trim());
    }
}
