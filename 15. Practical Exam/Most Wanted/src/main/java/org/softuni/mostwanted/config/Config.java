package org.softuni.mostwanted.config;

public final class Config {

    public static final String CARS_IMPORT_JSON = "/files/json/input/cars.json";
    public static final String DISTRICTS_IMPORT_JSON = "/files/json/input/districts.json";
    public static final String RACERS_IMPORT_JSON = "/files/json/input/racers.json";
    public static final String TOWNS_IMPORT_JSON = "/files/json/input/towns.json";

    public static final String RACE_ENTRIES_IMPORT_XML = "/files/xml/input/race-entries.xml";
    public static final String RACES_IMPORT_XML = "/files/xml/input/races.xml";

    public static final String TOWNS_BY_RACERS_EXPORT_JSON = "/files/json/output/townsByRacers.json";
    public static final String RACERS_WITH_CARS_EXPORT_JSON = "/files/json/output/racersWithCars.json";

    public static final String MOST_WANTED_RACER_EXPORT_XML = "/files/xml/output/mostWantedRacer.xml";

    public static final String ERROR_DUPLICATE_DATA = "Error: Duplicate Data!";
    public static final String ERROR_INCORRECT_DATA = "Error: Incorrect Data!";
    public static final String SUCCESSFULLY_IMPORTED = "Successfully imported %s – %s.";

    private Config() {
    }
}
