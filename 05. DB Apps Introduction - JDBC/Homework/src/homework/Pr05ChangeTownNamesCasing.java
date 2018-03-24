package homework;

import homework.constants.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pr05ChangeTownNamesCasing {

    private static final String TOWN_NAMES_WERE_AFFECTED = " town names were affected.";

    private static final String TOWNS_TO_UPPER_CASE = "SELECT t.id, t.name FROM `towns` AS t WHERE t.country = ?";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, Constants.DEFAULT_ENCODING);
        String countryName = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             PreparedStatement townsFromCountryStatement = connection.prepareStatement(
                     TOWNS_TO_UPPER_CASE, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            townsFromCountryStatement.setString(1, countryName);

            try (ResultSet towns = townsFromCountryStatement.executeQuery()) {
                if (!towns.isBeforeFirst()) {
                    System.out.println(Constants.NO_TOWN_NAMES_WERE_AFFECTED);
                    return;
                }

                while (towns.next()) {
                    String town = towns.getString(Constants.NAME);
                    towns.updateString(Constants.NAME, town.toUpperCase());
                    towns.updateRow();
                }

                List<String> townsUppercase = new ArrayList<>();

                towns.beforeFirst();
                while (towns.next()) {
                    townsUppercase.add(towns.getString(Constants.NAME));
                }
                System.out.println(townsUppercase.size() + TOWN_NAMES_WERE_AFFECTED);
                System.out.println(townsUppercase);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
