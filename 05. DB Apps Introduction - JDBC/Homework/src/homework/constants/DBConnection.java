package homework.constants;

public final class DBConnection {

    public static final String URL_HOST;
    public static final String URL_DATABASE;

    private static final String JDBC = "jdbc";
    private static final String DRIVER = "mysql";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String PARAMETERS = "useSSL=false";

    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        URL_HOST = String.format("%s:%s://%s:%s?user=%s&password=%s&%s",
                JDBC, DRIVER, HOST, PORT, USER, PASSWORD, PARAMETERS);

        URL_DATABASE = String.format("%s:%s://%s:%s/%s?user=%s&password=%s&%s",
                JDBC, DRIVER, HOST, PORT, Tables.DATABASE, USER, PASSWORD, PARAMETERS);
    }

    private DBConnection() {
    }
}
