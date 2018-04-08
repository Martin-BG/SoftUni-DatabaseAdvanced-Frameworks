package game_store.model.dto.view;

import game_store.model.enums.Role;
import game_store.model.validators.user_name.UserName;

public class LoggedInUserDto {

    private Long id;
    @UserName
    private String fullName;
    private Role role;

    public LoggedInUserDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }
}
