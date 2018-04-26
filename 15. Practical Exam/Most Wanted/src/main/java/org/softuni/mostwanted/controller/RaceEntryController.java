package org.softuni.mostwanted.controller;

import org.softuni.mostwanted.model.dto.binding.xml.RaceEntriesImportXmlDto;
import org.softuni.mostwanted.model.dto.binding.xml.RaceEntryImportXmlDto;
import org.softuni.mostwanted.parser.DataImporter;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.persistance.service.api.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class RaceEntryController {

    private final RaceEntryService service;
    private final DataImporter dataImporter;
    private final Parser parser;

    @Autowired
    public RaceEntryController(final RaceEntryService service,
                               final DataImporter dataImporter,
                               @Qualifier("XMLParser") final Parser parser) {
        this.service = service;
        this.dataImporter = dataImporter;
        this.parser = parser;
    }

    public String importFromXML(String xmlContent) {
        RaceEntriesImportXmlDto entries = parser.read(RaceEntriesImportXmlDto.class, xmlContent);
        return this.dataImporter.importData(
                entries.getEntries().toArray(new RaceEntryImportXmlDto[0]),
                this.service);
    }

    public String exportMostWantedRacer() {
        return this.parser.write(
                this.service.getMostWantedRacer());
    }
}
