package org.softuni.ruk.config;

public final class Config {

    public static final String BRANCHES_IMPORT_JSON = "/files/json/input/branches.json";
    public static final String CLIENTS_IMPORT_JSON = "/files/json/input/clients.json";
    public static final String EMPLOYEES_IMPORT_JSON = "/files/json/input/employees.json";

    public static final String BANK_ACCOUNTS_IMPORT_XML = "/files/xml/input/bank-accounts.xml";
    public static final String CARDS_IMPORT_XML = "/files/xml/input/cards.xml";

    public static final String TOP_EMPLOYEES_EXPORT_JSON = "/files/json/output/topEmployees.json";

    public static final String FAMILY_GUY_EXPORT_XML = "/files/xml/output/family-guy.xml";

    public static final String ERROR_INCORRECT_DATA = "Error: Incorrect Data!";
    public static final String SUCCESSFULLY_IMPORTED = "Successfully imported %s – %s.";

    private Config() {
    }
}
