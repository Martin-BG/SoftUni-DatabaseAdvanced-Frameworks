package hw;

import hw.constants.Constants;

import java.sql.*;
import java.util.Scanner;

public class Pr08IncreaseMinionsAge {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] inputTokens = sc.nextLine().split(Constants.TOKENS_SEPARATOR);

        StringBuilder ids = new StringBuilder();
        ids.append("(");
        for (String inputToken : inputTokens) {
            ids.append(inputToken).append(", ");
        }
        ids.delete(ids.lastIndexOf(", "), ids.length());
        ids.append(")");

        String minionsSQL = "SELECT * FROM minions WHERE id IN " + ids.toString();
        String allMinionsSQL = "SELECT * FROM minions";

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             Statement minionsStatement = connection.createStatement(
                     ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
             Statement allMinionsStatement = connection.createStatement()) {

            ResultSet minions = minionsStatement.executeQuery(minionsSQL);

            while (minions.next()) {
                String name = toTitleCase(minions.getString(Constants.NAME));
                int age = minions.getInt(Constants.AGE) + 1;
                minions.updateString(Constants.NAME, name);
                minions.updateInt(Constants.AGE, age);
                minions.updateRow();
            }

            ResultSet allMinions = allMinionsStatement.executeQuery(allMinionsSQL);

            while (allMinions.next()) {
                System.out.println(allMinions.getInt(Constants.ID) + Constants.SEPARATOR
                        + allMinions.getString(Constants.NAME) + Constants.SEPARATOR
                        + allMinions.getInt(Constants.AGE));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
