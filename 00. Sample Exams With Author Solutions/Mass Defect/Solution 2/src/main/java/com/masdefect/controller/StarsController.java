package com.masdefect.controller;

import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class StarsController {
    @Autowired
    private StarService starService;

    @Autowired
    @Qualifier(value = "JSONParser")
    private FileParser jsonParser;

    public String importDataFromJSON(String fileContent){
        StarImportJSONDto[] StarImportJSONDtos = null;
        try {
            StarImportJSONDtos = this.jsonParser.read(StarImportJSONDto[].class, fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (StarImportJSONDto starImportJSONDto : StarImportJSONDtos) {
            try{
                this.starService.create(starImportJSONDto);
                sb.append(String.format("Successfully imported Star %s.",starImportJSONDto.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e){
                sb.append(String.format("Error: Invalid data."));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
