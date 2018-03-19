import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/soft_uni?createDatabaseIfNotExist=true&useSSL=false";
        String user = "root";
        String password = "root";

        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {
//            Class.forName("com.mysql.jdbc.Driver");
            String sql = "SELECT * FROM soft_uni.`employees` AS e WHERE e.salary > 50000";
            statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
