package com.masdefect.terminal;

import com.masdefect.config.Config;
import com.masdefect.domain.dto.json.*;
import com.masdefect.domain.dto.xml.AnomaliesXMLDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.io.interfaces.ConsoleIO;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    @Qualifier(value = "JSONParser")
    private FileParser jsonParser;

    @Autowired
    @Qualifier(value = "XMLParser")
    private FileParser xmlParser;

    @Autowired
    private ConsoleIO consoleIO;

    @Autowired
    private SolarSystemService solarSystemService;

    @Autowired
    private StarService starService;

    @Autowired
    private PlanetService planetService;

    @Autowired
    private PersonService personService;

    @Autowired
    private AnomalyService anomalyService;

    @Override
    public void run(String... strings) throws Exception {
        //Imports
        this.importSolarSystemsFromJSON();
        this.importStarsFromJSON();
        this.importPlanetsFromJSON();
        this.importPersonsFromJSON();
        this.importAnomaliesFromJSON();
        this.importAnomalyVictimsFromJSON();
        this.importAnomaliesFromXML();
        //Exports
        this.exportPlanetsToJson();
        this.exportPersonsToJson();
        this.exportAnomaliesToJson();
        this.exportAnomaliesToXML();
    }

    private void importSolarSystemsFromJSON() {
        SolarSystemImportJSONDto[] solarSystemImportJSONDtos = null;
        try {
            solarSystemImportJSONDtos = this.jsonParser.read(SolarSystemImportJSONDto[].class, Config.SOLAR_SYSTEM_IMPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (SolarSystemImportJSONDto solarSystemImportJSONDto : solarSystemImportJSONDtos) {
            try {
                this.solarSystemService.create(solarSystemImportJSONDto);
                this.consoleIO.write(String.format("Successfully imported Solar System %s.", solarSystemImportJSONDto.getName()));
            } catch (Exception e) {
                this.consoleIO.write(String.format("Error: Invalid data."));
            }
        }
    }

    private void importStarsFromJSON() {
        StarImportJSONDto[] StarImportJSONDtos = null;
        try {
            StarImportJSONDtos = this.jsonParser.read(StarImportJSONDto[].class, Config.STARS_IMPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (StarImportJSONDto starImportJSONDto : StarImportJSONDtos) {
            try {
                this.starService.create(starImportJSONDto);
                this.consoleIO.write(String.format("Successfully imported Star %s.", starImportJSONDto.getName()));
            } catch (Exception e) {
                this.consoleIO.write(String.format("Error: Invalid data."));
            }
        }
    }

    private void importPlanetsFromJSON() {
        PlanetImportJSONDto[] planetImportJSONDtos = null;
        try {
            planetImportJSONDtos = this.jsonParser.read(PlanetImportJSONDto[].class, Config.PLANETS_IMPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (PlanetImportJSONDto planetImportJSONDto : planetImportJSONDtos) {
            try {
                this.planetService.create(planetImportJSONDto);
                this.consoleIO.write(String.format("Successfully imported Planet %s.", planetImportJSONDto.getName()));
            } catch (Exception e) {
                this.consoleIO.write(String.format("Error: Invalid data."));
            }
        }
    }

    private void importAnomaliesFromJSON() {
        AnomalyImpotJSONDto[] anomalyImpotJSONDtos = null;
        try {
            anomalyImpotJSONDtos = this.jsonParser.read(AnomalyImpotJSONDto[].class, Config.ANOMALY_IMPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (AnomalyImpotJSONDto anomalyImpotJSONDto : anomalyImpotJSONDtos) {
            try {
                this.anomalyService.create(anomalyImpotJSONDto);
                this.consoleIO.write(String.format("Successfully imported Anomaly."));
            } catch (Exception e) {
                this.consoleIO.write(String.format("Error: Invalid data."));
            }
        }
    }

    private void importAnomalyVictimsFromJSON() {
        AnomalyVictimsJSONDto[] anomalyVictimsDtos = null;
        try {
            anomalyVictimsDtos = this.jsonParser.read(AnomalyVictimsJSONDto[].class, Config.ANOMALY_VICTIMS_IMPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (AnomalyVictimsJSONDto anomalyVictimsDto : anomalyVictimsDtos) {
            try {
                this.anomalyService.create(anomalyVictimsDto);
                this.consoleIO.write(String.format("Successfully imported Anomaly."));
            } catch (Exception e) {
                this.consoleIO.write(String.format("Error: Invalid data."));
            }
        }
    }

    private void importAnomaliesFromXML() {
        AnomaliesXMLDto anomaliesImportXMLDtos = null;
        try {
            anomaliesImportXMLDtos = this.xmlParser.read(AnomaliesXMLDto.class, Config.ANOMALIES_IMPORT_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (AnomalyXMLDto anomalyImportXMLDto : anomaliesImportXMLDtos.getAnomalies()) {
            try {
                this.anomalyService.create(anomalyImportXMLDto);
                this.consoleIO.write(String.format("Successfully imported Anomaly."));
            } catch (Exception e) {
                this.consoleIO.write(String.format("Error: Invalid data."));
            }
        }
    }

    private void importPersonsFromJSON() {
        PersonImportJSONDto[] personImportJSONDtos = null;
        try {
            personImportJSONDtos = this.jsonParser.read(PersonImportJSONDto[].class, Config.PERSONS_IMPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (PersonImportJSONDto personImportJSONDto : personImportJSONDtos) {
            try {
                this.personService.create(personImportJSONDto);
                this.consoleIO.write(String.format("Successfully imported Person %s.", personImportJSONDto.getName()));
            } catch (Exception e) {
                this.consoleIO.write(String.format("Error: Invalid data."));
            }
        }
    }

    private void exportPlanetsToJson() {
        List<PlanetExportJSONDto> planetExportJSONDtoList = this.planetService.findAllPlanetsWithoutPeopleTeleportedFromThem();
        try {
            this.jsonParser.write(planetExportJSONDtoList, Config.PLANETS_EXPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportPersonsToJson() {
        List<PersonExportJSONDto> personExportJSONDtos = this.personService.findInnocentPersons();
        try {
            this.jsonParser.write(personExportJSONDtos, Config.PERSONS_EXPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportAnomaliesToJson() {
        AnomalyExportJSONDto anomalyExportJSONDto = this.anomalyService.findMostAffectingAnomalies();
        try {
            this.jsonParser.write(anomalyExportJSONDto, Config.ANOMALIES_EXPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportAnomaliesToXML() {
        AnomaliesXMLDto anomaliesXMLDto = this.anomalyService.finaAllAnomalies();
        try {
            this.xmlParser.write(anomaliesXMLDto, Config.ANOMALIES_EXPORT_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
