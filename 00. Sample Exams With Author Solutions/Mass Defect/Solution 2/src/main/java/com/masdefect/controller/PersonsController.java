package com.masdefect.controller;

import com.masdefect.config.Config;
import com.masdefect.domain.dto.json.PersonImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.PersonService;
import com.masdefect.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PersonsController {
    @Autowired
    private PersonService personService;

    @Autowired
    @Qualifier(value = "JSONParser")
    private FileParser jsonParser;

    public String importDataFromJSON(String fileContent){
        PersonImportJSONDto[] personImportJSONDtos = null;
        try {
            personImportJSONDtos = this.jsonParser.read(PersonImportJSONDto[].class, fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (PersonImportJSONDto personImportJSONDto : personImportJSONDtos) {
            try{
                this.personService.create(personImportJSONDto);
                sb.append(String.format("Successfully imported Person %s.",personImportJSONDto.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e){
               sb.append(String.format("Error: Invalid data."));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
