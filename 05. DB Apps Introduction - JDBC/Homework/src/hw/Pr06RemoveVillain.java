package hw;

import hw.constants.Constants;

import java.sql.*;
import java.util.Scanner;

public class Pr06RemoveVillain {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, Constants.DEFAULT_ENCODING);
        int id = Integer.parseInt(scanner.nextLine());

        String villainNameSQL = String.format("SELECT name FROM villains WHERE id = %d", id);
        String deleteVillainSQL = String.format("DELETE FROM villains where id = %d", id);
        String countMinionsSQL = String.format("SELECT COUNT(minion_id) AS c FROM villains v JOIN minions_villains mv ON v.id = mv.villain_id WHERE v.id = %d", id);
        String releaseMinionsSQL = String.format("DELETE FROM minions_villains WHERE villain_id = %d", id);

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE);
             Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);

            try {
                ResultSet villain = statement.executeQuery(villainNameSQL);
                if (!villain.isBeforeFirst()) {
                    System.out.println("No such villain was found");
                    return;
                }

                villain.first();
                String villainName = villain.getString("name");
                int minionsFound = 0;
                ResultSet minionsForVillain = statement.executeQuery(countMinionsSQL);
                if (minionsForVillain.isBeforeFirst()) {
                    minionsForVillain.first();
                    minionsFound = minionsForVillain.getInt("c");
                }

                statement.executeUpdate(releaseMinionsSQL);
                statement.executeUpdate(deleteVillainSQL);

                System.out.println(villainName + " was deleted");
                System.out.println(minionsFound + " minions released");

            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e.toString(), e.getSQLState(), e.getCause());
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
