package homework;

import homework.constants.DBConnection;
import homework.constants.Messages;
import homework.constants.SQLCreateTables;
import homework.constants.SQLTestData;
import homework.persistance.DatabaseManager;

import java.sql.SQLException;

public class Pr01InitialSetup {

    public static void main(String[] args) {

        System.out.println(Messages.INITIAL_SETUP);

        recreateMinionsDatabaseAndAddData();

        System.out.println();
    }

    private static void recreateMinionsDatabaseAndAddData() {
        if (createMinionsDatabase()) {
            System.out.println(Messages.DATABASE_CREATED_SUCCESSFULLY);
        } else {
            System.out.println(Messages.ERROR_CREATING_DATABASE);
        }

        if (createTablesInMinionsDatabase()) {
            System.out.println(Messages.TABLES_CREATED_SUCCESSFULLY);
        } else {
            System.out.println(Messages.ERROR_CREATING_TABLES);
        }

        if (populateTablesInMinionsDatabase()) {
            System.out.println(Messages.DATA_INSERTED_INTO_TABLES);
        } else {
            System.out.println(Messages.ERROR_INSERTING_DATA_INTO_TABLES);
        }
    }

    private static boolean createMinionsDatabase() {
        try {
            DatabaseManager.getInstance().executeStatements(
                    DBConnection.URL_HOST,
                    null,
                    SQLCreateTables.DROP_DATABASE,
                    SQLCreateTables.CREATE_DATABASE);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static boolean createTablesInMinionsDatabase() {
        try {
            DatabaseManager.getInstance().executeStatements(
                    DBConnection.URL_DATABASE,
                    null,
                    SQLCreateTables.CREATE_TABLE_TOWNS,
                    SQLCreateTables.CREATE_TABLE_MINIONS,
                    SQLCreateTables.CREATE_TABLE_VILLAINS,
                    SQLCreateTables.CREATE_TABLE_MINIONS_VILLAINS);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static boolean populateTablesInMinionsDatabase() {
        try {
            DatabaseManager.getInstance().executeStatements(
                    DBConnection.URL_DATABASE,
                    null,
                    SQLTestData.INSERT_INTO_TOWNS,
                    SQLTestData.INSERT_INTO_MINIONS,
                    SQLTestData.INSERT_INTO_VILLAINS,
                    SQLTestData.INSERT_INTO_MINIONS_VILLAINS);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
