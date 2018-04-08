package game_store.constants;

public final class CommandMessages {

    public static final String INVALID_COMMAND_ARGUMENTS = "Invalid command arguments!";
    public static final String INVALID_COMMAND = "Invalid command!";

    public static final String USER_WAS_REGISTERED = "%s was registered";
    public static final String CANNOT_REGISTER_USER_EMAIL_ALREADY_USED = "Cannot register user - email already used!";
    public static final String GAME_BY_THAT_NAME_ALREADY_EXIST = "Game by that name already exist!";
    public static final String GAME_ADDED = "Added %s";
    public static final String INVALID_GAME_ID = "Invalid game ID!";
    public static final String GAME_EDITED = "Edited %s";
    public static final String UNKNOWN_PARAMETER_TYPE = "Unknown game parameter type: %s";
    public static final String GAME_DELETED = "Deleted %s";

    private CommandMessages() {
    }
}
