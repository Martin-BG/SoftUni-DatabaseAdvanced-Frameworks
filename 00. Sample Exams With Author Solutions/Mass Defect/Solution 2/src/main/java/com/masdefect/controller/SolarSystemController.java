package com.masdefect.controller;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class SolarSystemController {
    @Autowired
    private SolarSystemService solarSystemService;

    @Autowired
    @Qualifier(value = "JSONParser")
    private FileParser jsonParser;

    public String importDataFromJSON(String fileContent){
        SolarSystemImportJSONDto[] solarSystemImportJSONDtos = null;
        try {
            solarSystemImportJSONDtos = this.jsonParser.read(SolarSystemImportJSONDto[].class, fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (SolarSystemImportJSONDto solarSystemImportJSONDto : solarSystemImportJSONDtos) {
            try{
                this.solarSystemService.create(solarSystemImportJSONDto);
                sb.append(String.format("Successfully imported Solar System %s.",solarSystemImportJSONDto.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e){
                sb.append(String.format("Error: Invalid data."));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
