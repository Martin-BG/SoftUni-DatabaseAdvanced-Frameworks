package homework;

import homework.constants.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Pr02GetVillainsNames {

    private static final String SELECT_VILLAINS_WITH_MORE_THAN_THREE_MINIONS = String.format(
            "" +
                    "SELECT%n" +
                    "    *%n" +
                    "FROM%n" +
                    "    (SELECT%n" +
                    "        v.name AS 'villain', COUNT(mv.minion_id) AS 'minions'%n" +
                    "    FROM%n" +
                    "        `%s` AS v%n" +
                    "    JOIN `%s` AS mv ON v.id = mv.villain_id%n" +
                    "    GROUP BY v.id%n" +
                    "    ORDER BY `minions` DESC) AS vm%n" +
                    "WHERE%n" +
                    "    vm.minions >= 3",
            Constants.TABLE_VILLAINS, Constants.TABLE_MINIONS_VILLAINS);

    public static void main(String[] args) {

        System.out.println(getVillainsWithAtLeastThreeMinions());

    }

    private static String getVillainsWithAtLeastThreeMinions() {
        try {
            List<Map<String, Object>> result = executeQuery(
                    Constants.URL_DATABASE,
                    SELECT_VILLAINS_WITH_MORE_THAN_THREE_MINIONS);

            return ResultsParser.getInstance()
                    .parseResults(result, true, Constants.SEPARATOR, 0, -1);

        } catch (SQLException e) {
            e.printStackTrace();
            return Constants.DATABASE_ERROR;
        }
    }

    private static List<Map<String, Object>> executeQuery(String Host, String query) throws SQLException {
        try (Connection connection = DriverManager.getConnection(Host);
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
