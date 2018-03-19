import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Pr01ConnectionAndProperties {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/soft_uni?createDatabaseIfNotExist=true&useSSL=false";
        String user = "root";
        String password = "root";

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        try (Connection connection = DriverManager.getConnection(url, properties);
             Scanner scanner = new Scanner(System.in)) {

            String sql =
                    "SELECT " +
                            "CONCAT_WS(' ', e.first_name, e.last_name) AS 'full_name', e.salary\n" +
                    "FROM " +
                            "`employees` AS e\n" +
                    "WHERE " +
                            "e.salary > ?\n" +
                    "ORDER BY " +
                            "e.salary DESC\n" +
                    "LIMIT 50";

            PreparedStatement stmt = connection.prepareStatement(sql);

            System.out.print("Enter min salary: ");
            stmt.setDouble(1, Double.parseDouble(scanner.nextLine()));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.printf("%s: %.2f%n",
                        rs.getString("full_name"),
                        rs.getDouble("salary"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
