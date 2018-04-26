package org.softuni.ruk.conroller;

import org.softuni.ruk.parser.DataImporter;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.persistance.service.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ClientController {

    private final ClientService service;
    private final DataImporter importer;
    private final Parser parser;
    private final Parser parserXml;

    @Autowired
    public ClientController(final ClientService service,
                            final DataImporter importer,
                            @Qualifier("JSONParser") final Parser parser,
                            @Qualifier("XMLParser") final Parser parserXml) {
        this.service = service;
        this.importer = importer;
        this.parser = parser;
        this.parserXml = parserXml;
    }

    public <T> String importFromJSON(String json, final Class<T[]> clazz) {
        return this.importer.importData(
                this.parser,
                clazz,
                json,
                this.service);
    }

    public String exportClientWithMostCards() {
        return this.parserXml.write(
                this.service.findClientWithMostCards());
    }
}
