package homework.constants;

public final class Constants {

    public static final String URL_HOST;
    public static final String URL_DATABASE;

    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String ID = "id";

    public static final String TABLE_TOWNS = "towns";
    public static final String TABLE_MINIONS = "minions";
    public static final String TABLE_VILLAINS = "villains";
    public static final String TABLE_MINIONS_VILLAINS = "minions_villains";

    public static final String DEFAULT_ENCODING = "UTF-8";

    public static final String INPUT_SEPARATOR = "\\s+";
    public static final String OUTPUT_SEPARATOR = " ";

    public static final String DATABASE_CREATED_SUCCESSFULLY = "Database created successfully";
    public static final String ERROR_CREATING_DATABASE = "Error creating database";
    public static final String TABLES_CREATED_SUCCESSFULLY = "Tables created successfully";
    public static final String ERROR_CREATING_TABLES = "Error creating tables";
    public static final String DATA_INSERTED_INTO_TABLES = "Data inserted into tables";
    public static final String ERROR_INSERTING_DATA_INTO_TABLES = "Error inserting data into tables";
    public static final String DATABASE_ERROR = "Database error";
    public static final String INVALID_VILLAIN_ID = "No villain with ID %d exists in the database.";
    public static final String NO_MINIONS = "<no minions>";
    public static final String VILLAIN = "Villain: ";
    public static final String ENTER_VILLAIN_ID = "Enter villain id: ";
    public static final String VILLAIN_ADDED = "Villain %s was added to the database.%n";
    public static final String TOWN_ADDED = "Town %s was added to the database.%n";
    public static final String ADDED_MINION_TO_VILLAIN = "Successfully added %s to be minion of %s";
    public static final String NO_TOWN_NAMES_WERE_AFFECTED = "No town names were affected.";

    private static final String JDBC = "jdbc";
    private static final String DRIVER = "mysql";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String PARAMETERS = "useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DATABASE = "minions_db";

    static {
        URL_HOST = String.format("%s:%s://%s:%s?user=%s&password=%s&%s",
                JDBC, DRIVER, HOST, PORT, USER, PASSWORD, PARAMETERS);

        URL_DATABASE = String.format("%s:%s://%s:%s/%s?user=%s&password=%s&%s",
                JDBC, DRIVER, HOST, PORT, DATABASE, USER, PASSWORD, PARAMETERS);
    }

    private Constants() {
    }
}
