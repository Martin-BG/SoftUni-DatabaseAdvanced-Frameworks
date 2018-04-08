package game_store.commands;

import game_store.commands.manage.AddGame;
import game_store.commands.manage.DeleteGame;
import game_store.commands.manage.EditGame;
import game_store.commands.user.Login;
import game_store.commands.user.Logout;
import game_store.commands.user.Register;
import game_store.constants.CommandConstants;
import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    private final UserService userService;
    private final GameService gameService;

    private CommandFactory(final UserService userService, final GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    public Executable getCommand(String command) {
        switch (command) {
        case CommandConstants.REGISTER_USER:
            return new Register(this.userService, this.gameService);
        case CommandConstants.LOGIN_USER:
            return new Login(this.userService, this.gameService);
        case CommandConstants.LOGOUT_USER:
            return new Logout(this.userService, this.gameService);
        case CommandConstants.ADD_GAME:
            return new AddGame(this.userService, this.gameService);
        case CommandConstants.EDIT_GAME:
            return new EditGame(this.userService, this.gameService);
        case CommandConstants.DELETE_GAME:
            return new DeleteGame(this.userService, this.gameService);
        default:
            return null;
        }
    }
}
