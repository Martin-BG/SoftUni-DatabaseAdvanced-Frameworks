package homework;

import homework.constants.Constants;

import java.sql.*;
import java.util.Scanner;

public class Pr06RemoveVillain {

    private static final String NO_SUCH_VILLAIN_WAS_FOUND = "No such villain was found";
    private static final String WAS_DELETED = " was deleted";
    private static final String MINIONS_RELEASED = " minions released";

    private static final String GET_VILLAIN_FROM_ID = "SELECT name FROM villains WHERE id = %d";
    private static final String DELETE_VILLAIN_BY_ID = "DELETE FROM villains where id = %d";
    private static final String COUNT_MINIONS_FOR_VILLAIN = "SELECT COUNT(mv.minion_id) AS cnt " +
            "FROM `villains` AS v JOIN `minions_villains` AS mv ON v.id = mv.villain_id WHERE v.id = %d";
    private static final String DELETE_FROM_MV_VILLAIN_BY_ID = "DELETE FROM minions_villains WHERE villain_id = %d";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, Constants.DEFAULT_ENCODING);
        int id = Integer.parseInt(scanner.nextLine());

        String villainNameSQL = String.format(GET_VILLAIN_FROM_ID, id);
        String deleteVillainSQL = String.format(DELETE_VILLAIN_BY_ID, id);
        String countMinionsSQL = String.format(COUNT_MINIONS_FOR_VILLAIN, id);
        String releaseMinionsSQL = String.format(DELETE_FROM_MV_VILLAIN_BY_ID, id);

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             Statement statement = connection.createStatement();
             ResultSet villain = statement.executeQuery(villainNameSQL)) {

            if (!villain.isBeforeFirst()) {
                System.out.println(NO_SUCH_VILLAIN_WAS_FOUND);
                return;
            }

            villain.first();
            String villainName = villain.getString(Constants.NAME);
            int minionsFound = 0;

            connection.setAutoCommit(false);

            try (ResultSet minionsForVillain = statement.executeQuery(countMinionsSQL)) {
                if (minionsForVillain.isBeforeFirst()) {
                    minionsForVillain.first();
                    minionsFound = minionsForVillain.getInt("cnt");
                }

                statement.executeUpdate(releaseMinionsSQL);
                statement.executeUpdate(deleteVillainSQL);

                connection.commit();

                System.out.println(villainName + WAS_DELETED);
                System.out.println(minionsFound + MINIONS_RELEASED);
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
