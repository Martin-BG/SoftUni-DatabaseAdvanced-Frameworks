package game_store.constants;

public final class ValidationConstrains {

    public static final String USER_EMAIL_REGEX = "^\\S+@\\S+.\\S+$";
    public static final int USER_EMAIL_MIN_LENGTH = 5;
    public static final int USER_EMAIL_MAX_LENGTH = 100;
    public static final boolean USER_EMAIL_CAN_BE_EMPTY = false;

    // public static final String USER_PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,30}$";
    public static final int USER_PASSWORD_MIN_LENGTH = 6;
    public static final int USER_PASSWORD_MAX_LENGTH = 30;
    public static final boolean USER_PASSWORD_SHOULD_CONTAIN_LOWER_CASE = true;
    public static final boolean USER_PASSWORD_SHOULD_CONTAIN_UPPER_CASE = true;
    public static final boolean USER_PASSWORD_SHOULD_CONTAIN_DIGIT = true;
    public static final boolean USER_PASSWORD_CAN_BE_OMITTED = false;

    public static final int USER_NAME_MIN_LENGTH = 1;
    public static final int USER_NAME_MAX_LENGTH = 60;

    public static final int GAME_DESC_MIN_LENGTH = 20;
    public static final int GAME_DESC_MAX_LENGTH = 1000;
    public static final boolean GAME_DESC_CAN_BE_OMITTED = false;

    public static final String GAME_THUMBNAIL_URL_REGEX = "(?i)^https?://\\S+$";
    public static final int GAME_THUMBNAIL_URL_MAX_LENGTH = 100;
    public static final boolean GAME_THUMBNAIL_URL_CAN_BE_OMITTED = true;

    //public static final String GAME_TITLE_REGEX = "^(?=[A-Z]).{1,100}$";
    public static final int GAME_TITLE_MIN_LENGTH = 1;
    public static final int GAME_TITLE_MAX_LENGTH = 100;
    public static final boolean GAME_TITLE_UPPER_FIRST_LETTER = true;
    public static final boolean GAME_TITLE_CAN_BE_OMITTED = false;

    public static final String GAME_TRAILER_REGEX = "^[a-zA-Z0-9]+$";
    public static final int GAME_TRAILER_MIN_LENGTH = 11;
    public static final int GAME_TRAILER_MAX_LENGTH = 11;
    public static final boolean GAME_TRAILER_CAN_BE_OMITTED = true;

    public static final int GAME_SIZE_MAX_PRECISION = 1;
    public static final boolean GAME_SIZE_CAN_BE_OMITTED = false;
    public static final boolean GAME_SIZE_CAN_BE_NEGATIVE = false;

    public static final int GAME_PRICE_MAX_PRECISION = 2;
    public static final boolean GAME_PRICE_CAN_BE_OMITTED = false;
    public static final boolean GAME_PRICE_CAN_BE_NEGATIVE = false;

    private ValidationConstrains() {
    }
}
