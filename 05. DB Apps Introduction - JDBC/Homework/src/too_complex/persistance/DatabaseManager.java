package too_complex.persistance;

import too_complex.constants.Messages;

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

    public List<Map<String, Object>> executePreparedStatement(
            String Host, Properties properties, String query, Map<ParameterType, Object> params) throws SQLException {
        try (Connection connection = DriverManager.getConnection(Host, properties)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                int index = 1;
                for (Map.Entry<ParameterType, Object> kvp : params.entrySet()) {
                    switch (kvp.getKey()) {
                    case INTEGER:
                        preparedStatement.setInt(index++, (Integer) kvp.getValue());
                        break;
                    case STRING:
                        preparedStatement.setString(index++, (String) kvp.getValue());
                        break;
                    default:
                        throw new SQLException(Messages.UNKNOWN_PARAMETER_TYPE);
                    }

                }

                connection.setAutoCommit(false);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<Map<String, Object>> resultList = new ArrayList<>();

                    ResultSetMetaData metaData = resultSet.getMetaData();
                    Integer columnCount = metaData.getColumnCount();

                    while (resultSet.next()) {
                        Map<String, Object> row = new LinkedHashMap<>();

                        for (int i = 1; i <= columnCount; i++) {
                            row.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                        }

                        resultList.add(row);
                    }

                    connection.commit();

                    return resultList;
                } catch (SQLException e) {
                    connection.rollback();
                    throw new SQLException(e.getCause());
                }

            } catch (SQLException e) {
                throw new SQLException(e.getCause());
            }
        }
    }

    public int executeUpdate(String Host, Properties properties, String query,
                             Map<ParameterType, Object> params) throws SQLException {
        try (Connection connection = DriverManager.getConnection(Host, properties)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                int index = 1;
                for (Map.Entry<ParameterType, Object> kvp : params.entrySet()) {
                    switch (kvp.getKey()) {
                    case INTEGER:
                        preparedStatement.setInt(index++, (Integer) kvp.getValue());
                        break;
                    case STRING:
                        preparedStatement.setString(index++, (String) kvp.getValue());
                        break;
                    default:
                        throw new SQLException(Messages.UNKNOWN_PARAMETER_TYPE);
                    }

                }

                connection.setAutoCommit(false);

                try {
                    int affectedRows = preparedStatement.executeUpdate();

                    connection.commit();

                    return affectedRows;

                } catch (SQLException e) {
                    connection.rollback();
                    throw new SQLException(e.getCause());
                }

            } catch (SQLException e) {
                throw new SQLException(e.getCause());
            }
        }
    }
}
