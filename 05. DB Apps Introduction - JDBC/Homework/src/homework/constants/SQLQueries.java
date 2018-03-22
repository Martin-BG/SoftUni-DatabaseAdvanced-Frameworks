package homework.constants;

public final class SQLQueries {

    public static final String SELECT_VILLAINS_WITH_MORE_THAN_THREE_MINIONS = String.format(
            "" +
                    "SELECT%n" +
                    "    *%n" +
                    "FROM%n" +
                    "    (SELECT%n" +
                    "        v.name AS 'villain', COUNT(mv.minion_id) AS 'minions'%n" +
                    "    FROM%n" +
                    "        `%s` AS v%n" +
                    "    JOIN `%s` AS mv ON v.id = mv.villain_id%n" +
                    "    GROUP BY v.id%n" +
                    "    ORDER BY `minions` DESC) AS vm%n" +
                    "WHERE%n" +
                    "    vm.minions >= 3",
            Tables.TABLE_VILLAINS, Tables.TABLE_MINIONS_VILLAINS);

    public static final String GET_VILLAIN_NAME_FROM_ID = String.format(
            "" +
                    "SELECT %n" +
                    "    v.name%n" +
                    "FROM%n" +
                    "    `%s` AS v%n" +
                    "WHERE%n" +
                    "    v.id = ?",
            Tables.TABLE_VILLAINS
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
            Tables.TABLE_VILLAINS, Tables.TABLE_MINIONS_VILLAINS, Tables.TABLE_MINIONS);

    public static final String FIND_TOWN_ID_BY_NAME = String.format(
            "" +
                    "SELECT %n" +
                    "    c.id%n" +
                    "FROM%n" +
                    "    `%s` AS c%n" +
                    "WHERE%n" +
                    "    c.name = ?",
            Tables.TABLE_TOWNS);
    public static final String INSERT_NEW_TOWN = String.format(
            "" +
                    "INSERT INTO `%s`%n" +
                    "    (`name`, `country`)%n" +
                    "VALUES%n" +
                    "    (?, 'Unknown')",
            Tables.TABLE_TOWNS);

    private SQLQueries() {
    }
}
