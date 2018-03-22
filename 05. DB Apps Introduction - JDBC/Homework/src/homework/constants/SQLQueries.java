package homework.constants;

public final class SQLQueries {

    public static final String SELECT_VILLAINS_WITH_MORE_THAN_THREE_MINIONS = String.format(
            "" +
                    "SELECT%n" +
                    "    *%n" +
                    "FROM%n" +
                    "    (SELECT%n" +
                    "        v.name, COUNT(mv.minion_id) AS 'mn'%n" +
                    "    FROM%n" +
                    "        `%s` AS v%n" +
                    "    JOIN `%s` AS mv ON v.id = mv.villain_id%n" +
                    "    GROUP BY v.id%n" +
                    "    ORDER BY `mn` DESC) AS vm%n" +
                    "WHERE%n" +
                    "    vm.mn >= 3",
            Tables.TABLE_VILLAINS, Tables.TABLE_MINIONS_VILLAINS);

    private SQLQueries() {
    }
}
