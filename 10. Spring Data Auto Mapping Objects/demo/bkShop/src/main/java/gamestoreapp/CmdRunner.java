package gamestoreapp;

import gamestoreapp.model.dto.binding.UserLoginBindingModel;
import gamestoreapp.model.dto.binding.UserRegisterBindingModel;
import gamestoreapp.model.dto.view.SuccessLoginUserViewModel;
import gamestoreapp.services.game.GameService;
import gamestoreapp.services.role.RoleService;
import gamestoreapp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class CmdRunner implements CommandLineRunner {

    private final GameService gameService;
    private final UserService userService;
    private final RoleService roleService;
    private final Set<Long> usersInSystem;

    @Autowired
    public CmdRunner(GameService gameService,
                     UserService userService,
                     RoleService roleService) {
        this.gameService = gameService;
        this.userService = userService;
        this.roleService = roleService;
        this.usersInSystem = new HashSet<>();
    }

    @Override
    public void run(String... strings) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input[] = reader.readLine().split("\\|");

        switch (input[0]) {
        case "RegisterUser":
            UserRegisterBindingModel registerDto = new UserRegisterBindingModel(input[1], input[2], input[3], input[4]);
            boolean isRegistered = this.userService.register(registerDto);

            if (isRegistered) {
                System.out.println(registerDto.getB() + " was registered");
            }
            break;
        case "LoginUser":
            UserLoginBindingModel loginDto = new UserLoginBindingModel(input[1], input[2]);
            SuccessLoginUserViewModel successLoginUserViewModel = this.userService.login(loginDto);

            if (successLoginUserViewModel != null) {
                this.usersInSystem.add(successLoginUserViewModel.getId());
                System.out.println("Successfully logged in " + successLoginUserViewModel.getFullName());
            }
            break;

        }
    }
}
