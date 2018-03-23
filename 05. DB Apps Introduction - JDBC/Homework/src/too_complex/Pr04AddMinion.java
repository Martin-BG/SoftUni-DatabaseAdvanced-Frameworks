package too_complex;

import too_complex.constants.DBConnection;
import too_complex.constants.Globals;
import too_complex.constants.Messages;
import too_complex.constants.SQLQueries;
import too_complex.persistance.DatabaseManager;
import too_complex.persistance.ParameterType;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Pr04AddMinion {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, Globals.DEFAULT_ENCODING);

        System.out.print(Messages.MINION);
        String[] tokens = scanner.nextLine().trim().split(Globals.TOKENS_SEPARATOR);
        String minionNme = tokens[1];
        int age = Integer.parseInt(tokens[2]);
        String town = tokens[3];

        System.out.print(Messages.VILLAIN);
        tokens = scanner.nextLine().trim().split(Globals.TOKENS_SEPARATOR);
        String villainName = tokens[1];

        // Check for town
        Map<ParameterType, Object> parameters = new LinkedHashMap<>();
        parameters.put(ParameterType.STRING, town);


        int villainId;
        try {
            List<Map<String, Object>> result = DatabaseManager.getInstance().executePreparedStatement(
                    DBConnection.URL_DATABASE,
                    null,
                    SQLQueries.FIND_TOWN_ID_BY_NAME,
                    parameters);

            if (result.isEmpty()) {
                DatabaseManager.getInstance().executeUpdate(
                        DBConnection.URL_DATABASE,
                        null,
                        SQLQueries.INSERT_NEW_TOWN,
                        parameters);

                System.out.printf(Messages.TOWN_ADDED_TO_DATABASE, town);
            }

            result = DatabaseManager
                    .getInstance()
                    .executePreparedStatement(
                            DBConnection.URL_DATABASE,
                            null,
                            SQLQueries.FIND_TOWN_ID_BY_NAME,
                            parameters);

            // Check for villain
            parameters.clear();
            parameters.put(ParameterType.STRING, villainName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
