package game_store.constants;

import java.time.format.DateTimeFormatter;

public final class CommandConstants {

    public static final String INPUT_COMMANDS_SEPARATOR = "\\|";
    public static final String INPUT_PARAMS_SEPARATOR = "=";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d-M-yyyy");

    public static final String REGISTER_USER = "RegisterUser";
    public static final String ADD_GAME = "AddGame";
    public static final String EDIT_GAME = "EditGame";
    public static final String DELETE_GAME = "DeleteGame";
    public static final String LOGIN_USER = "LoginUser";
    public static final String LOGOUT_USER = "LogoutUser";
    public static final String END_COMMAND = "End";

    private CommandConstants() {
    }
}
