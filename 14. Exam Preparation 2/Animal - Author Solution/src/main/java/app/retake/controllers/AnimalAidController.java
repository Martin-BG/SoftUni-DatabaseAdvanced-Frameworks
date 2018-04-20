package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class AnimalAidController {

    private final AnimalAidService animalAidService;
    private final Parser parser;

    @Autowired
    public AnimalAidController(AnimalAidService animalAidService,
                               @Qualifier("JSONParser") Parser parser) {
        this.animalAidService = animalAidService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            AnimalAidJSONImportDTO[] animalAids = parser.read(AnimalAidJSONImportDTO[].class, jsonContent);
            Arrays.stream(animalAids).forEach(a -> {
                if (ValidationUtil.isValid(a)) {
                    this.animalAidService.create(a);
                    sb.append(String.format("Record %s successfully imported.", a.getName()))
                            .append(System.lineSeparator());
                } else {
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }

            });
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
