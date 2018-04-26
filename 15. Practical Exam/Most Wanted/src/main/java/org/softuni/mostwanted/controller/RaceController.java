package org.softuni.mostwanted.controller;

import org.softuni.mostwanted.model.dto.binding.xml.RaceImportXmlDto;
import org.softuni.mostwanted.model.dto.binding.xml.RacesImportXmlDto;
import org.softuni.mostwanted.parser.DataImporter;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.persistance.service.api.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class RaceController {

    private final RaceService service;
    private final DataImporter dataImporter;
    private final Parser parser;

    @Autowired
    public RaceController(final RaceService service,
                          final DataImporter dataImporter,
                          @Qualifier("XMLParser") final Parser parser) {
        this.service = service;
        this.dataImporter = dataImporter;
        this.parser = parser;
    }

    public String importFromXML(String xmlContent) {
        RacesImportXmlDto entries = parser.read(RacesImportXmlDto.class, xmlContent);
        return this.dataImporter.importData(
                entries.getEntries().toArray(new RaceImportXmlDto[0]),
                this.service);
    }
}
