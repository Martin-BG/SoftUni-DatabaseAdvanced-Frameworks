package homework;

import homework.constants.Constants;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Pr08IncreaseMinionsAge {

    private static final String SOME_MINIONS_SQL = "SELECT * FROM minions WHERE id IN ?";
    private static final String ALL_MINIONS_SQL = "SELECT * FROM minions";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in, Constants.DEFAULT_ENCODING);
        String ids = Arrays.toString(sc.nextLine().split(Constants.INPUT_SEPARATOR))
                .replaceAll("\\[", "(").replaceAll("]", ")");

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             Statement minionsStatement = connection.createStatement(
                     ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
             PreparedStatement allMinionsStatement = connection.prepareStatement(ALL_MINIONS_SQL);
             ResultSet minions = minionsStatement.executeQuery(SOME_MINIONS_SQL)) {

            while (minions.next()) {
                String name = toTitleCase(minions.getString(Constants.NAME));
                int age = minions.getInt(Constants.AGE) + 1;
                minions.updateString(Constants.NAME, name);
                minions.updateInt(Constants.AGE, age);
                minions.updateRow();
            }

            allMinionsStatement.setString(1, ids);
            try (ResultSet allMinions = allMinionsStatement.executeQuery(ALL_MINIONS_SQL)) {
                while (allMinions.next()) {
                    System.out.println(allMinions.getInt(Constants.ID) + Constants.OUTPUT_SEPARATOR
                            + allMinions.getString(Constants.NAME) + Constants.OUTPUT_SEPARATOR
                            + allMinions.getInt(Constants.AGE));
                }
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
