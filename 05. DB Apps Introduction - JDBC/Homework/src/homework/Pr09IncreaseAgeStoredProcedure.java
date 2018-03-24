package homework;

import homework.constants.Constants;

import java.sql.*;
import java.util.Scanner;


public class Pr09IncreaseAgeStoredProcedure {

    public static void main(String[] args) {

        String getOlderSQL = "{CALL usp_get_older (?)}";
        String minionSQL = "SELECT m.name, m.age FROM `minions` AS m WHERE id = ?";

        Scanner sc = new Scanner(System.in, Constants.DEFAULT_ENCODING);
        int id = sc.nextInt();

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             CallableStatement getOlderStoredProcedure = connection.prepareCall(getOlderSQL);
             PreparedStatement minionsStatement = connection.prepareStatement(minionSQL)) {

            getOlderStoredProcedure.setInt(1, id);
            getOlderStoredProcedure.execute();

            minionsStatement.setInt(1, id);
            try (ResultSet minions = minionsStatement.executeQuery()) {
                minions.first();
                System.out.println(minions.getString(Constants.NAME) +
                        Constants.OUTPUT_SEPARATOR + minions.getInt(Constants.AGE));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
