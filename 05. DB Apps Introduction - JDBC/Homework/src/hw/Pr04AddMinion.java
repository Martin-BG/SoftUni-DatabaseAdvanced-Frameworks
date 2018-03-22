package hw;

import hw.constants.Constants;

import java.sql.*;
import java.util.Scanner;

public class Pr04AddMinion {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, Constants.DEFAULT_ENCODING);

        String[] tokens = scanner.nextLine().split(Constants.TOKENS_SEPARATOR);
        String minionName = tokens[1];
        int minionAge = Integer.parseInt(tokens[2]);
        String minionTown = tokens[3];
        String villainName = scanner.nextLine().split(Constants.TOKENS_SEPARATOR)[1];

        String townSQL = String.format("SELECT t.id FROM `%s` AS t WHERE t.name = ?", Constants.TABLE_TOWNS);
        String addTownSQL = String.format("INSERT INTO `%s` (`name`, `country`) VALUES (?, 'Unknown')", Constants.TABLE_TOWNS);
        String villainSQL = String.format("SELECT * FROM `%s` AS v WHERE v.name = ?", Constants.TABLE_VILLAINS);
        String addVillainSQL = String.format("INSERT INTO `%s` (`name`, `evilness_factor`) VALUES (?, 'evil')", Constants.TABLE_VILLAINS);
        String addMinionSQL = String.format("INSERT INTO `%s` (`name`, `age`, `town_id`) VALUES (?, ?, ?)", Constants.TABLE_MINIONS);
        String getMinionIdSQL = String.format("SELECT m.id FROM `%s` AS m WHERE m.name = ?", Constants.TABLE_MINIONS);
        String addMinionToVillainSQL = String.format("INSERT INTO `%s` (`minion_id`, `villain_id`) VALUES (?, ?)", Constants.TABLE_MINIONS_VILLAINS);

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             PreparedStatement townStatement = connection.prepareStatement(townSQL);
             PreparedStatement addTownStatement = connection.prepareStatement(addTownSQL);
             PreparedStatement villainStatement = connection.prepareStatement(villainSQL);
             PreparedStatement addVillainStatement = connection.prepareStatement(addVillainSQL);
             PreparedStatement addMinionStatement = connection.prepareStatement(addMinionSQL);
             PreparedStatement getNewMinionIdStatement = connection.prepareStatement(getMinionIdSQL);
             PreparedStatement addMinionToVillainStatement = connection.prepareStatement(addMinionToVillainSQL)) {

            //connection.setAutoCommit(false);

            try {
                townStatement.setString(1, minionTown);

                ResultSet towns = townStatement.executeQuery();

                if (!towns.isBeforeFirst()) {
                    //add town to db

                    addTownStatement.setString(1, minionTown);
                    addTownStatement.executeUpdate();

                    System.out.printf("Town %s was added to the database.%n", minionTown);
                }

                towns = townStatement.executeQuery();
                towns.first();
                int townID = towns.getInt(Constants.ID);

                villainStatement.setString(1, villainName);

                ResultSet villain = villainStatement.executeQuery();
                if (!villain.isBeforeFirst()) {
                    //add villain to db

                    addVillainStatement.setString(1, villainName);
                    addVillainStatement.executeUpdate();

                    System.out.printf("Villain %s was added to the database.%n", villainName);
                }

                villain = villainStatement.executeQuery();
                villain.first();
                int villainID = villain.getInt(Constants.ID);

                //add minion

                addMinionStatement.setString(1, minionName);
                addMinionStatement.setInt(2, minionAge);
                addMinionStatement.setInt(3, townID);
                addMinionStatement.executeUpdate();

                //get minion ID

                getNewMinionIdStatement.setString(1, minionName);
                ResultSet minions = getNewMinionIdStatement.executeQuery();
                minions.first();
                int minionID = minions.getInt(Constants.ID);

                //add record in villains_minions table

                addMinionToVillainStatement.setInt(1, minionID);
                addMinionToVillainStatement.setInt(2, villainID);
                addMinionToVillainStatement.executeUpdate();

                System.out.printf("Successfully added %s to be minion of %s", minionName, villainName);

                // connection.commit();
            } catch (Exception e) {
                // connection.rollback();
                throw new SQLException(e.getCause());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
