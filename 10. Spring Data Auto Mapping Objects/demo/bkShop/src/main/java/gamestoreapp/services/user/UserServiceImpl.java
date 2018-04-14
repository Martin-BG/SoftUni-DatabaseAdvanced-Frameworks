package gamestoreapp.services.user;

import gamestoreapp.model.dto.binding.UserLoginBindingModel;
import gamestoreapp.model.dto.binding.UserRegisterBindingModel;
import gamestoreapp.model.dto.view.SuccessLoginUserViewModel;
import gamestoreapp.model.entity.Role;
import gamestoreapp.model.entity.User;
import gamestoreapp.repositories.UserRepository;
import gamestoreapp.services.role.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public boolean register(UserRegisterBindingModel model) {
        User user = this.modelMapper.map(model, User.class);
        Role role = this.setUserRole(user);
        if (model.isPasswordMatch()) {
            user = this.userRepository.saveAndFlush(user);
            role.getUsers().add(user);
            this.roleService.updateRole(role);
        }
        return user.getId() != null;
    }

    @Override
    public SuccessLoginUserViewModel login(UserLoginBindingModel model) {
        User user = this.userRepository.findOneByEmail(model.getEmail());
        if (user != null) {
            if (user.getPassword().equals(model.getPassword())) {
                return this.modelMapper.map(user, SuccessLoginUserViewModel.class);
            }
        }
        return null;
    }

    private Role setUserRole(User user) {
        Role role;
        if (this.userRepository.count() > 0) {
            role = this.roleService.getRoleByName(RoleService.Roles.USER);
            user.getRoles().add(role);
        } else {
            role = this.roleService.getRoleByName(RoleService.Roles.ADMIN);
            user.getRoles().add(role);
        }
        return role;
    }
}