package app.retake.controllers;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class AnimalController {

    private final AnimalService animalService;
    private final Parser parser;

    @Autowired
    public AnimalController(AnimalService animalService,
                            @Qualifier("JSONParser") Parser parser) {
        this.animalService = animalService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            AnimalJSONImportDTO[] animals = parser.read(AnimalJSONImportDTO[].class, jsonContent);
            Arrays.stream(animals).forEach(a -> {
                boolean hasInvalidData = false;
                if (ValidationUtil.isValid(a)) {
                    try {

                        this.animalService.create(a);
                    } catch (IllegalArgumentException e) {
                        hasInvalidData = true;
                    }
                    if (hasInvalidData) {
                        sb.append("Error: Invalid data.").append(System.lineSeparator());
                    } else {
                        sb.append(String.format("Record %s Passport №: %s successfully imported.", a.getName(), a.getPassport().getSerialNumber()))
                                .append(System.lineSeparator());
                    }

                } else {
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }

            });
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        try {
            return this.parser.write(this.animalService.findByOwnerPhoneNumber(phoneNumber));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
