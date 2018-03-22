package hw;

import hw.constants.Constants;

import java.sql.*;
import java.util.Scanner;

public class Pr03GetMinionNames {

    public static final String GET_VILLAIN_NAME_FROM_ID = String.format(
            "" +
                    "SELECT %n" +
                    "    v.name%n" +
                    "FROM%n" +
                    "    `%s` AS v%n" +
                    "WHERE%n" +
                    "    v.id = ?",
            Constants.TABLE_VILLAINS
    );

    public static final String GET_MINIONS_FOR_VILLAIN = String.format(
            "" +
                    "SELECT %n" +
                    "    m.name, m.age%n" +
                    "FROM%n" +
                    "    `%s` AS v%n" +
                    "        JOIN%n" +
                    "    `%s` AS mv ON v.id = mv.villain_id%n" +
                    "        JOIN%n" +
                    "    `%s` AS m ON m.id = mv.minion_id%n" +
                    "WHERE%n" +
                    "    v.id = ?",
            Constants.TABLE_VILLAINS, Constants.TABLE_MINIONS_VILLAINS, Constants.TABLE_MINIONS);

    public static void main(String[] args) {

        try {
            System.out.println(getAllMinionsForOneVillain());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static String getAllMinionsForOneVillain() throws SQLException {

        System.out.print(Constants.ENTER_VILLAIN_ID);
        Scanner scanner = new Scanner(System.in, Constants.DEFAULT_ENCODING);
        int villainId = Integer.parseInt(scanner.nextLine());

        try (Connection connection = DriverManager.getConnection(Constants.URL_DATABASE)) {

            try (PreparedStatement psVillain = connection.prepareStatement(GET_VILLAIN_NAME_FROM_ID);
                 PreparedStatement psMinions = connection.prepareStatement(GET_MINIONS_FOR_VILLAIN)) {

                psVillain.setInt(1, villainId);
                psMinions.setInt(1, villainId);

                connection.setAutoCommit(false);

                try (ResultSet rsVillain = psVillain.executeQuery();
                     ResultSet rsMinion = psMinions.executeQuery()) {

                    connection.commit();

                    if (!rsVillain.isBeforeFirst()) {
                        return String.format(Constants.INVALID_VILLAIN_ID, villainId);
                    }

                    StringBuilder sb = new StringBuilder();

                    rsVillain.first();
                    String villainName = rsVillain.getString(Constants.NAME);
                    sb.append(Constants.VILLAIN).append(villainName).append(System.lineSeparator());

                    if (!rsMinion.isBeforeFirst()) {
                        sb.append(Constants.NO_MINIONS);
                    } else {
                        int index = 1;
                        while (rsMinion.next()) {
                            sb.append(index++).append(". ")
                                    .append(rsMinion.getString(Constants.NAME))
                                    .append(" ")
                                    .append(rsMinion.getString(Constants.AGE))
                                    .append(System.lineSeparator());
                        }
                    }
                    return sb.toString().trim();
                } catch (SQLException e) {
                    connection.rollback();
                    throw new SQLException(e.getCause());
                }
            }
        }
    }
}