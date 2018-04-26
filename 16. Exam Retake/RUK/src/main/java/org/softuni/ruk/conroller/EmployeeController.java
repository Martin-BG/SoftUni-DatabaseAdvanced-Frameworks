package org.softuni.ruk.conroller;

import org.softuni.ruk.parser.DataImporter;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.persistance.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

    private final EmployeeService service;
    private final DataImporter importer;
    private final Parser parser;

    @Autowired
    public EmployeeController(final EmployeeService service,
                              final DataImporter importer,
                              @Qualifier("JSONParser") final Parser parser) {
        this.service = service;
        this.importer = importer;
        this.parser = parser;
    }

    public <T> String importFromJSON(String json, final Class<T[]> clazz) {
        return this.importer.importData(
                this.parser,
                clazz,
                json,
                this.service);
    }

    public String exportAllByClients() {
        return this.parser.write(
                this.service.getEmployeesByClients());
    }
}
