package too_complex;

import too_complex.constants.*;
import too_complex.persistance.DatabaseManager;
import too_complex.persistance.ParameterType;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Pr03GetMinionNames {

    public static void main(String[] args) {

        System.out.println(Messages.GET_MINION_NAMES);

        System.out.println(getAllMinionsForOneVillain());

        System.out.println();
    }

    private static String getAllMinionsForOneVillain() {

        System.out.print(Messages.ENTER_VILLAIN_ID);
        Scanner scanner = new Scanner(System.in, Globals.DEFAULT_ENCODING);
        int villainId = Integer.parseInt(scanner.nextLine());

        Map<ParameterType, Object> parameters = new LinkedHashMap<>();
        parameters.put(ParameterType.INTEGER, villainId);

        try {
            List<Map<String, Object>> result = DatabaseManager.getInstance().executePreparedStatement(
                    DBConnection.URL_DATABASE,
                    null,
                    SQLQueries.GET_VILLAIN_NAME_FROM_ID,
                    parameters);

            if (result.isEmpty()) {
                return String.format(Messages.INVALID_VILLAIN_ID, villainId);
            }

            StringBuilder sb = new StringBuilder();

            sb.append(Messages.VILLAIN)
                    .append(result.get(0).get(Tables.NAME))
                    .append(System.lineSeparator());

            result = DatabaseManager.getInstance().executePreparedStatement(
                    DBConnection.URL_DATABASE,
                    null,
                    SQLQueries.GET_MINIONS_FOR_VILLAIN,
                    parameters);

            if (result.isEmpty()) {

                sb.append(Messages.NO_MINIONS);

            } else {

                int minion = 1;

                for (Map<String, Object> row : result) {

                    sb.append(minion++).append(". ");

                    for (Object obj : row.values()) {
                        sb.append(obj).append(Messages.SEPARATOR);
                    }

                    sb.append(System.lineSeparator());
                }
            }

            return sb.toString();

        } catch (SQLException e) {

            e.printStackTrace();

            return Messages.DATABASE_ERROR;
        }
    }
}
