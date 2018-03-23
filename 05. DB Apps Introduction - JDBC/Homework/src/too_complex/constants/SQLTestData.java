package too_complex.constants;

public final class SQLTestData {
    public static final String INSERT_INTO_TOWNS;
    public static final String INSERT_INTO_MINIONS;
    public static final String INSERT_INTO_VILLAINS;
    public static final String INSERT_INTO_MINIONS_VILLAINS;

    static {
        INSERT_INTO_TOWNS = String.format(
                "" +
                        "INSERT INTO `%s`%n" +
                        "    (`name`, `country`)%n" +
                        "VALUES %n" +
                        "    ('Sofia', 'Bulgaria'),%n" +
                        "    ('Varna', 'Bulgaria'),%n" +
                        "    ('Plovdiv', 'Bulgaria'),%n" +
                        "    ('Berlin', 'Germany'),%n" +
                        "    ('Paris', 'France')",
                Tables.TABLE_TOWNS);

        INSERT_INTO_MINIONS = String.format(
                "" +
                        "INSERT INTO `%s`%n" +
                        "    (`name`, `age`, `town_id`)%n" +
                        "VALUES%n" +
                        "    ('Bobo', 10, 1),%n" +
                        "    ('Kevin', 13, 3),%n" +
                        "    ('Steward', 8, 2),%n" +
                        "    ('Jimmy', 16, 5),%n" +
                        "    ('Dodo', 23, 4)",
                Tables.TABLE_MINIONS);

        INSERT_INTO_VILLAINS = String.format(
                "" +
                        "INSERT INTO `%s`%n" +
                        "    (`name`, `evilness_factor`)%n" +
                        "VALUES%n" +
                        "    ('Gru', 'good'),%n" +
                        "    ('Victor', 'super evil'),%n" +
                        "    ('Koko', 'evil'),%n" +
                        "    ('Juji', 'bad'),%n" +
                        "    ('Misho', 'good'),%n" +
                        "    ('Empty', 'evil')",
                Tables.TABLE_VILLAINS);

        INSERT_INTO_MINIONS_VILLAINS = String.format(
                "" +
                        "INSERT INTO `%s`%n" +
                        "VALUES%n" +
                        "    (1, 1),%n" +
                        "    (3, 1),%n" +
                        "    (1, 2),%n" +
                        "    (2, 2),%n" +
                        "    (4, 5),%n" +
                        "    (2, 1),%n" +
                        "    (5, 5),%n" +
                        "    (4, 1),%n" +
                        "    (4, 2),%n" +
                        "    (4, 3),%n" +
                        "    (5, 1),%n" +
                        "    (5, 2),%n" +
                        "    (5, 3),%n" +
                        "    (5, 4),%n" +
                        "    (3, 3);",
                Tables.TABLE_MINIONS_VILLAINS);

    }

    private SQLTestData() {
    }
}
