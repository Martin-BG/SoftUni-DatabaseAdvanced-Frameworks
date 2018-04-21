package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.dto.Importable;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class AnimalAidController {

    private final AnimalAidService animalAidService;
    private final Parser parser;
    private final Importer importer;

    @Autowired
    public AnimalAidController(final AnimalAidService animalAidService,
                               @Qualifier("JSONParser") final Parser parser,
                               final Importer importer) {
        this.animalAidService = animalAidService;
        this.parser = parser;
        this.importer = importer;
    }

    public String importDataFromJSON(String jsonContent) {
        return this.importData(AnimalAidJSONImportDTO[].class, jsonContent);
    }

    private <T extends Importable> String importData(Class<T[]> clazz, String data) {
        return this.importer.importData(
                this.parser,
                clazz,
                data,
                this.animalAidService);
    }
}
