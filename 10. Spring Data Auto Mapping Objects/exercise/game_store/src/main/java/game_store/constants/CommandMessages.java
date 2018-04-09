package game_store.constants;

public final class CommandMessages {

    public static final String INVALID_COMMAND_ARGUMENTS = "Invalid command arguments!";
    public static final String INVALID_COMMAND = "Invalid command!";
    public static final String INVALID_USER_OR_PASSWORD = "Invalid user or password!";
    public static final String INVALID_GAME_ID = "Invalid game ID!";
    public static final String UNKNOWN_PARAMETER_TYPE = "Unknown game parameter type: %s";

    public static final String USER_WAS_REGISTERED = "%s was registered";
    public static final String CANNOT_REGISTER_USER_EMAIL_ALREADY_USED = "Cannot register user - email already used!";
    public static final String GAME_BY_THAT_NAME_ALREADY_EXIST = "Game by that name already exist!";
    public static final String GAME_NOT_FOUND = "Game not found!";
    public static final String GAME_ADDED = "Added %s";
    public static final String GAME_EDITED = "Edited %s";
    public static final String GAME_DELETED = "Deleted %s";
    public static final String USER_LOGGED_IN = "Successfully logged in %s";
    public static final String NO_USER_LOGGED_IN = "Cannot log out. No user was logged in.";
    public static final String USER_LOGGED_OUT = "User %s successfully logged out";
    public static final String ADMIN_REQUIRED = "Only administrators are authorised for this action!";
    public static final String VIEW_TITLE_PRICE = "%s %.2f%n";
    public static final String NO_CURRENT_USER = "This action as valid only for logged in users!";
    public static final String OWNED_GAMES = "Owned games list:%n";

    private CommandMessages() {
    }
}
