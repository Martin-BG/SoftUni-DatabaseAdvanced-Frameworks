package homework;

import homework.constants.DBConnection;
import homework.constants.Messages;
import homework.constants.SQLQueries;
import homework.persistance.DatabaseManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Pr02GetVillainsNames {

    public static void main(String[] args) {

        System.out.println(Messages.GET_VILLAINS_NAMES);

        System.out.println(getVillainsWithAtLeastThreeMinions());

        System.out.println();
    }

    private static String getVillainsWithAtLeastThreeMinions() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Map<String, Object>> result = DatabaseManager.getInstance().executeQuery(
                    DBConnection.URL_DATABASE,
                    null,
                    SQLQueries.SELECT_VILLAINS_WITH_MORE_THAN_THREE_MINIONS);

            for (Map<String, Object> aResult : result) {
                for (Object obj : aResult.values()) {
                    sb.append(obj).append(Messages.SEPARATOR);
                }

                sb.append(System.lineSeparator());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Messages.ERROR_GETTING_VILLAINS_FROM_DATABASE;
        }

        return sb.toString().trim();
    }
}
