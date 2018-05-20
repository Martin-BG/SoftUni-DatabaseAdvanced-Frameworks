package com.masdefect.controller;

import com.masdefect.config.Config;
import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.PlanetService;
import com.masdefect.service.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlanetsController {
    @Autowired
    private PlanetService planetService;

    @Autowired
    @Qualifier(value = "JSONParser")
    private FileParser jsonParser;

    public String importDataFromJSON(String fileContent){
        PlanetImportJSONDto[] planetImportJSONDtos = null;
        try {
            planetImportJSONDtos = this.jsonParser.read(PlanetImportJSONDto[].class, fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (PlanetImportJSONDto planetImportJSONDto : planetImportJSONDtos) {
            try{
                this.planetService.create(planetImportJSONDto);
                sb.append(String.format("Successfully imported Planet %s.",planetImportJSONDto.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e){
                sb.append(String.format("Error: Invalid data."));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String planetsWithNoPeopleTeleportedToThem(){
        List<PlanetExportJSONDto> planetExportJSONDtoList = this.planetService.findAllPlanetsWithoutPeopleTeleportedFromThem();
        String output = "";
        try {
            output = this.jsonParser.write(planetExportJSONDtoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}
