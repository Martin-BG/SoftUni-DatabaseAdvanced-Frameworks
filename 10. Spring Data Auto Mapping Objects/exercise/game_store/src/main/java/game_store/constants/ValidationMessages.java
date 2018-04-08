package game_store.constants;

public final class ValidationMessages {

    public static final String EMAIL_INVALID = "Invalid email!";
    public static final String EMAIL_TOO_LONG = "Email too long!";
    public static final String EMAIL_TOO_SHORT = "Email too short!";
    public static final String EMAIL_CANNOT_BE_EMPTY = "Email cannot be empty!";

    public static final String PASSWORD_TOO_SIMPLE = "Password doesn't meet complexity requirements!";
    public static final String PASSWORD_TOO_SHORT = "Password too short!";
    public static final String PASSWORD_TOO_LONG = "Password too long!";
    public static final String PASSWORD_CANNOT_BE_EMPTY = "Password cannot be empty!";
    public static final String PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER = "Password should contain lowercase letter!";
    public static final String PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER = "Password should contain uppercase letter!";
    public static final String PASSWORD_SHOULD_CONTAIN_DIGIT = "Password should contain digit!";

    public static final String PASSWORD_CONFIRM_INVALID = "Passwords mismatch!";

    public static final String USER_NAME_CANNOT_BE_EMPTY = "Name cannot be empty!";
    public static final String USER_NAME_TOO_SHORT = "Name too short!";
    public static final String USER_NAME_TOO_LONG = "Name too long!";

    public static final String GAME_THUMBNAIL_URL_TOO_LONG = "Thumbnail URL address too long!";
    public static final String GAME_THUMBNAIL_URL_INVALID = "Invalid thumbnail URL!";

    public static final String GAME_DESCRIPTION_TOO_SHORT = "Description too short!";
    public static final String GAME_DESCRIPTION_TOO_LONG = "Description too long!";
    public static final String GAME_DESCRIPTION_CANNOT_BE_EMPTY = "Description cannot be empty!";

    public static final String GAME_TITLE_SHOULD_START_WITH_CAPITAL_LETTER = "Game title should start with capital letter!";
    public static final String GAME_TITLE_CANNOT_BE_EMPTY = "Game title cannot be empty!";
    public static final String GAME_TITLE_TOO_SHORT = "Game title too short!";
    public static final String GAME_TITLE_TOO_LONG = "Game title too long!";

    public static final String GAME_INVALID_TRAILER_ID = "Invalid trailer ID!";

    public static final String GAME_SIZE_NEGATIVE = "Game size cannot be negative number!";
    public static final String GAME_SIZE_CANNOT_BE_EMPTY = "Game size cannot be empty!";
    public static final String GAME_SIZE_PRECISION_TOO_HIGH = "Game size precision too high!";

    public static final String GAME_PRICE_NEGATIVE = "Price cannot be negative!";
    public static final String GAME_PRICE_CANNOT_BE_EMPTY = "Price cannot be empty!";
    public static final String GAME_PRICE_PRECISION_TOO_HIGH = "Price precision too high!";

    private ValidationMessages() {
    }
}
