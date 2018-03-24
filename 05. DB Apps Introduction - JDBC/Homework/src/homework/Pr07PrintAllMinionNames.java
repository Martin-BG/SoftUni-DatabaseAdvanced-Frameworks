package homework;

import homework.constants.Constants;

import java.sql.*;

public class Pr07PrintAllMinionNames {

    private static final String minionNames = "SELECT m.name FROM `minions` AS m";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             PreparedStatement minionsStatement = connection.prepareStatement(
                     minionNames, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet minions = minionsStatement.executeQuery()) {

            int minionsCount = 0;
            while (minions.next()) {
                minionsCount++;
            }

            minions.beforeFirst();

            int firstIndex = 1;
            int lastIndex = minionsCount;

            for (int i = 1; i < minionsCount + 1; i++) {
                if (i % 2 != 0) {
                    minions.absolute(firstIndex);
                    firstIndex++;
                } else {
                    minions.absolute(lastIndex);
                    lastIndex--;
                }

                System.out.println(minions.getString(Constants.NAME));
                minions.next();
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
