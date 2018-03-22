package homework.persistance;

import java.sql.*;
import java.util.*;

public class DatabaseManager {

    private static final DatabaseManager DATABASE_MANAGER = new DatabaseManager();

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        return DATABASE_MANAGER;
    }

    public void executeStatements(String Host, Properties properties, String... statements) throws SQLException {
        try (Connection connection = DriverManager.getConnection(Host, properties)) {
            try (Statement statement = connection.createStatement()) {

                connection.setAutoCommit(false);

                for (String query : statements) {
                    statement.execute(query);
                }

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e.getCause());
            }
        }
    }

    public List<Map<String, Object>> executeQuery(String Host, Properties properties, String query) throws SQLException {
        try (Connection connection = DriverManager.getConnection(Host, properties);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            List<Map<String, Object>> resultList = new ArrayList<>();

            ResultSetMetaData metaData = resultSet.getMetaData();
            Integer columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new LinkedHashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), resultSet.getObject(i));
                }

                resultList.add(row);
            }

            return resultList;
        }
    }
}
