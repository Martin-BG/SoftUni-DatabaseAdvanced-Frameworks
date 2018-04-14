package gamestoreapp.seed;

import gamestoreapp.model.entity.Role;
import gamestoreapp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class RoleSeedExecutor {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleSeedExecutor(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void insertRoles() {
        if (this.roleRepository.count() == 0L) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            Role userRole = new Role();
            userRole.setName("USER");

            this.roleRepository.save(Arrays.asList(adminRole, userRole));
        }
    }
}
