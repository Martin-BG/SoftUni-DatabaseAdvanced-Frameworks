package app.retake.controllers;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.Importable;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class AnimalController {

    private final AnimalService animalService;
    private final Parser parser;
    private final Importer importer;

    public AnimalController(final AnimalService animalService,
                            @Qualifier("JSONParser") final Parser parser,
                            final Importer importer) {
        this.animalService = animalService;
        this.parser = parser;
        this.importer = importer;
    }

    public String importDataFromJSON(String jsonContent) {
        return this.importData(AnimalJSONImportDTO[].class, jsonContent);
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        return null;
    }

    private <T extends Importable> String importData(Class<T[]> clazz, String data) {
        return this.importer.importData(
                this.parser,
                clazz,
                data,
                this.animalService);
    }
}
