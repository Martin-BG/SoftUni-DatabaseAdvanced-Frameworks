package gamestoreapp.services.role;

import gamestoreapp.model.entity.Role;

public interface RoleService {

    Role getRoleByName(Roles role);

    void updateRole(Role role);

    enum Roles {
        ADMIN, USER
    }
}