package homework;

import homework.constants.Constants;

import java.sql.*;
import java.util.Scanner;

public class Pr04AddMinion {

    private static final String TOWN_SQL = String.format("SELECT t.id FROM `%s` AS t WHERE t.name = ?", Constants.TABLE_TOWNS);
    private static final String ADD_TOWN_SQL = String.format("INSERT INTO `%s` (`name`, `country`) VALUES (?, 'Unknown')", Constants.TABLE_TOWNS);
    private static final String VILLAIN_SQL = String.format("SELECT * FROM `%s` AS v WHERE v.name = ?", Constants.TABLE_VILLAINS);
    private static final String ADD_VILLAIN_SQL = String.format("INSERT INTO `%s` (`name`, `evilness_factor`) VALUES (?, 'evil')", Constants.TABLE_VILLAINS);
    private static final String ADD_MINION_SQL = String.format("INSERT INTO `%s` (`name`, `age`, `town_id`) VALUES (?, ?, ?)", Constants.TABLE_MINIONS);
    private static final String GET_MINION_ID_SQL = String.format("SELECT m.id FROM `%s` AS m WHERE m.name = ?", Constants.TABLE_MINIONS);
    private static final String ADD_MINION_TO_VILLAIN_SQL = String.format("INSERT INTO `%s` (`minion_id`, `villain_id`) VALUES (?, ?)", Constants.TABLE_MINIONS_VILLAINS);


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, Constants.DEFAULT_ENCODING);

        String[] tokens = scanner.nextLine().split(Constants.INPUT_SEPARATOR);
        String minionName = tokens[1];
        int minionAge = Integer.parseInt(tokens[2]);
        String minionTown = tokens[3];
        String villainName = scanner.nextLine().split(Constants.INPUT_SEPARATOR)[1];


        StringBuilder sb = new StringBuilder();

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             PreparedStatement townStatement = connection.prepareStatement(TOWN_SQL);
             PreparedStatement addTownStatement = connection.prepareStatement(ADD_TOWN_SQL);
             PreparedStatement villainStatement = connection.prepareStatement(VILLAIN_SQL);
             PreparedStatement addVillainStatement = connection.prepareStatement(ADD_VILLAIN_SQL);
             PreparedStatement addMinionStatement = connection.prepareStatement(ADD_MINION_SQL);
             PreparedStatement getNewMinionIdStatement = connection.prepareStatement(GET_MINION_ID_SQL);
             PreparedStatement addMinionToVillainStatement = connection.prepareStatement(ADD_MINION_TO_VILLAIN_SQL)) {

            connection.setAutoCommit(false);

            try {
                townStatement.setString(1, minionTown);

                ResultSet towns = townStatement.executeQuery();

                if (!towns.isBeforeFirst()) {
                    addTownStatement.setString(1, minionTown);
                    addTownStatement.executeUpdate();

                    sb.append(String.format(Constants.TOWN_ADDED, minionTown));
                }

                towns = townStatement.executeQuery();
                towns.first();
                int townID = towns.getInt(Constants.ID);

                villainStatement.setString(1, villainName);

                ResultSet villain = villainStatement.executeQuery();
                if (!villain.isBeforeFirst()) {
                    addVillainStatement.setString(1, villainName);
                    addVillainStatement.executeUpdate();

                    sb.append(String.format(Constants.VILLAIN_ADDED, villainName));
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

                sb.append(String.format(Constants.ADDED_MINION_TO_VILLAIN, minionName, villainName));

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(sb.toString().trim());
    }
}
