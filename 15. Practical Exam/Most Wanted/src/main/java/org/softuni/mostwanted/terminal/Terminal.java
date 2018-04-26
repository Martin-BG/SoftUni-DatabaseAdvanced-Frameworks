package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.config.Config;
import org.softuni.mostwanted.controller.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.model.dto.binding.json.CarImportJsonDto;
import org.softuni.mostwanted.model.dto.binding.json.DistrictImportJsonDto;
import org.softuni.mostwanted.model.dto.binding.json.RacerImportJsonDto;
import org.softuni.mostwanted.model.dto.binding.json.TownImportJsonDto;
import org.softuni.mostwanted.persistance.service.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final CarController carController;
    private final DistrictController districtController;
    private final RaceController raceController;
    private final RaceEntryController raceEntryController;
    private final RacerController racerController;
    private final TownController townController;
    private final TownService townService;

    @Autowired
    public Terminal(final FileIO fileIO,
                    final ConsoleIO consoleIO,
                    final CarController carController,
                    final DistrictController districtController,
                    final RaceController raceController,
                    final RaceEntryController raceEntryController,
                    final RacerController racerController,
                    final TownController townController, final TownService townService) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.carController = carController;
        this.districtController = districtController;
        this.raceController = raceController;
        this.raceEntryController = raceEntryController;
        this.racerController = racerController;
        this.townController = townController;
        this.townService = townService;
    }

    @Override
    public void run(String... args) {

        this.consoleIO.write(
                this.townController.importFromJSON(
                        fileIO.read(Config.TOWNS_IMPORT_JSON), TownImportJsonDto[].class));

        this.consoleIO.write(
                this.districtController.importFromJSON(
                        fileIO.read(Config.DISTRICTS_IMPORT_JSON), DistrictImportJsonDto[].class));

        this.consoleIO.write(
                this.racerController.importFromJSON(
                        fileIO.read(Config.RACERS_IMPORT_JSON), RacerImportJsonDto[].class));

        this.consoleIO.write(
                this.carController.importFromJSON(
                        this.fileIO.read(Config.CARS_IMPORT_JSON), CarImportJsonDto[].class));

        this.consoleIO.write(
                this.raceEntryController.importFromXML(
                        fileIO.read(Config.RACE_ENTRIES_IMPORT_XML)));

        this.consoleIO.write(
                this.raceController.importFromXML(
                        fileIO.read(Config.RACES_IMPORT_XML)));

        this.fileIO.write(
                this.townController.townsByRacers(),
                Config.TOWNS_BY_RACERS_EXPORT_JSON);

        this.fileIO.write(
                this.racerController.getAllRacersWithCars(),
                Config.RACERS_WITH_CARS_EXPORT_JSON);

        this.fileIO.write(
                this.raceEntryController.exportMostWantedRacer(),
                Config.MOST_WANTED_RACER_EXPORT_XML);
    }
}
