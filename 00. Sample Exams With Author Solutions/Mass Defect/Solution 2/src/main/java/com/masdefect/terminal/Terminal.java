package com.masdefect.terminal;

import com.masdefect.config.Config;
import com.masdefect.controller.*;
import com.masdefect.domain.dto.json.AnomalyVictimsJSONDto;
import com.masdefect.io.interfaces.ConsoleIO;
import com.masdefect.io.interfaces.FileIO;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    @Qualifier(value = "JSONParser")
    private FileParser jsonParser;

    @Autowired
    @Qualifier(value = "XMLParser")
    private FileParser xmlParser;

    @Autowired
    private FileIO fileParser;

    @Autowired
    private ConsoleIO consoleIO;

    @Autowired
    private AnomalyController anomalyController;

    @Autowired
    private SolarSystemController solarSystemController;

    @Autowired
    private StarsController starsController;

    @Autowired
    private PlanetsController planetsController;

    @Autowired
    private PersonsController personsController;

    @Autowired
    private AnomalyVictimsController anomalyVictimsController;

    @Autowired
    private AnomalyService anomalyService;

    @Override
    public void run(String... strings) throws Exception {
          this.consoleIO.write(this.solarSystemController.importDataFromJSON(this.fileParser.read(Config.SOLAR_SYSTEM_IMPORT_JSON)));
          this.consoleIO.write(this.starsController.importDataFromJSON(this.fileParser.read(Config.STARS_IMPORT_JSON)));
          this.consoleIO.write(this.planetsController.importDataFromJSON(this.fileParser.read(Config.PLANETS_IMPORT_JSON)));
          this.consoleIO.write(this.personsController.importDataFromJSON(this.fileParser.read(Config.PERSONS_IMPORT_JSON)));
          this.consoleIO.write(this.anomalyController.importDataFromJSON(this.fileParser.read(Config.ANOMALY_IMPORT_JSON)));
          this.consoleIO.write(this.anomalyController.importDataFromXML(this.fileParser.read(Config.ANOMALIES_IMPORT_XML)));
          this.consoleIO.write(this.anomalyVictimsController.importDataFromJSON(this.fileParser.read(Config.ANOMALY_VICTIMS_IMPORT_JSON)));
    }
}
