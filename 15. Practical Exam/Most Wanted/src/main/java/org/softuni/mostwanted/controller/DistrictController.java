package org.softuni.mostwanted.controller;

import org.softuni.mostwanted.parser.DataImporter;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.persistance.service.api.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class DistrictController extends DataImporter {

    private final DistrictService service;
    private final DataImporter dataImporter;
    private final Parser parser;

    @Autowired
    public DistrictController(final DistrictService service,
                              final DataImporter dataImporter,
                              @Qualifier("JSONParser") final Parser parser) {
        this.service = service;
        this.dataImporter = dataImporter;
        this.parser = parser;
    }

    public <T> String importFromJSON(String json, final Class<T[]> clazz) {
        return this.dataImporter.importData(
                this.parser,
                clazz,
                json,
                this.service);
    }
}
