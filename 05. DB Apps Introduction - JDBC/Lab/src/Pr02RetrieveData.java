import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Pr02RetrieveData {

    private static final String URL = "jdbc:mysql://localhost:3306/diablo?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final String SQL =
            "" +
                    "SELECT " +
                    "    CONCAT_WS(' ', u.first_name, u.last_name) AS full_name, " +
                    "    COUNT(ug.id) AS games " +
                    "FROM " +
                    "    `diablo`.`users` AS u " +
                    "        JOIN " +
                    "    `diablo`.`users_games` AS ug ON u.id = ug.user_id " +
                    "WHERE " +
                    "    u.user_name = ? " +
                    "GROUP BY u.id;";

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);

        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print("Enter player name: ");
        String userName = scanner.nextLine().trim();

        try (Connection connection = DriverManager.getConnection(URL, properties);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, userName);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.printf("User: %s%n%s has played %d games",
                            userName,
                            rs.getString("full_name"),
                            rs.getInt("games"));
                }

                if (!found) {
                    System.out.println("No such user exists");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
