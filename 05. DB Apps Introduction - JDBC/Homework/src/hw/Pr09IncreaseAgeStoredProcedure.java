package hw;

import hw.constants.Constants;

import java.sql.*;
import java.util.Scanner;


public class Pr09IncreaseAgeStoredProcedure {

    public static void main(String[] args) {

        String getOlderSQL = "{CALL usp_get_older (?)}";
        String minionSQL = "SELECT name, age FROM minions WHERE id = ?";

        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             CallableStatement getOlderStoredProcedure = connection.prepareCall(getOlderSQL);
             PreparedStatement minionsStatement = connection.prepareStatement(minionSQL)) {

            getOlderStoredProcedure.setInt(1, id);
            getOlderStoredProcedure.execute();

            minionsStatement.setInt(1, id);
            ResultSet minions = minionsStatement.executeQuery();

            minions.first();
            System.out.println(minions.getString(Constants.NAME) + " " + minions.getInt(Constants.AGE));

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
