package org.softuni.ruk.conroller;

import org.softuni.ruk.parser.DataImporter;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.persistance.service.api.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class BranchController {

    private final BranchService service;
    private final DataImporter importer;
    private final Parser parser;

    @Autowired
    public BranchController(final BranchService service,
                            final DataImporter importer,
                            @Qualifier("JSONParser") final Parser parser) {
        this.importer = importer;
        this.parser = parser;
        this.service = service;
    }

    public <T> String importFromJSON(String json, final Class<T[]> clazz) {
        return this.importer.importData(
                this.parser,
                clazz,
                json,
                this.service);
    }
}
