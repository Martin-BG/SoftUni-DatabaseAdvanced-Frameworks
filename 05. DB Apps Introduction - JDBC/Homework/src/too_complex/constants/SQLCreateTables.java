package too_complex.constants;

public final class SQLCreateTables {

    public static final String DROP_DATABASE;
    public static final String CREATE_DATABASE;

    public static final String CREATE_TABLE_TOWNS;
    public static final String CREATE_TABLE_MINIONS;
    public static final String CREATE_TABLE_VILLAINS;
    public static final String CREATE_TABLE_MINIONS_VILLAINS;

    static {
        DROP_DATABASE = String.format("DROP DATABASE IF EXISTS `%s`", Tables.DATABASE);

        CREATE_DATABASE = String.format("CREATE DATABASE `%s`", Tables.DATABASE);

        CREATE_TABLE_TOWNS = String.format(
                "" +
                        "CREATE TABLE `%s` (%n" +
                        "    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,%n" +
                        "    `name` VARCHAR(50) NOT NULL UNIQUE,%n" +
                        "    `country` VARCHAR(50) NOT NULL%n" +
                        ")",
                Tables.TABLE_TOWNS);

        CREATE_TABLE_MINIONS = String.format(
                "" +
                        "CREATE TABLE `%s` (%n" +
                        "    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,%n" +
                        "    `name` VARCHAR(50) NOT NULL UNIQUE,%n" +
                        "    `age` TINYINT UNSIGNED NOT NULL,%n" +
                        "    `town_id` INT UNSIGNED NOT NULL,%n" +
                        "    CONSTRAINT `fk_minions_towns` FOREIGN KEY (`town_id`)%n" +
                        "        REFERENCES `%s` (`id`)%n" +
                        ")",
                Tables.TABLE_MINIONS, Tables.TABLE_TOWNS);

        CREATE_TABLE_VILLAINS = String.format(
                "" +
                        "CREATE TABLE `%s` (%n" +
                        "    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,%n" +
                        "    `name` VARCHAR(50) NOT NULL UNIQUE,%n" +
                        "    `evilness_factor` ENUM('good', 'bad', 'evil', 'super evil') NOT NULL DEFAULT 'good'%n" +
                        ")",
                Tables.TABLE_VILLAINS);

        CREATE_TABLE_MINIONS_VILLAINS = String.format(
                "" +
                        "CREATE TABLE `%s` (%n" +
                        "    `minion_id` INT UNSIGNED NOT NULL,%n" +
                        "    `villain_id` INT UNSIGNED NOT NULL,%n" +
                        "    CONSTRAINT `fk_minions_villains_minions` FOREIGN KEY (`minion_id`)%n" +
                        "        REFERENCES `%s` (`id`),%n" +
                        "    CONSTRAINT `fk_minions_villains_villains` FOREIGN KEY (`villain_id`)%n" +
                        "        REFERENCES `%s` (`id`),%n" +
                        "    CONSTRAINT `pk_minions_villains` PRIMARY KEY (`minion_id` , `villain_id`)%n" +
                        ")",
                Tables.TABLE_MINIONS_VILLAINS, Tables.TABLE_MINIONS, Tables.TABLE_VILLAINS);
    }

    private SQLCreateTables() {
    }
}
