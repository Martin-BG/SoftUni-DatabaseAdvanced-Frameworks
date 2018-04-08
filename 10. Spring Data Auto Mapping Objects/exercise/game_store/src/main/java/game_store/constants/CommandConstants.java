package game_store.constants;

import java.time.format.DateTimeFormatter;

public final class CommandConstants {

    public static final String INPUT_COMMANDS_SEPARATOR = "\\|";
    public static final String INPUT_PARAMS_SEPARATOR = "=";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d-M-yyyy");

    public static final String REGISTER_USER = "RegisterUser";
    public static final String ADD_GAME = "AddGame";

    private CommandConstants() {
    }
}
