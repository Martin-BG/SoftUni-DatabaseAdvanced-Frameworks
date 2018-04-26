package org.softuni.ruk.conroller;

import org.softuni.ruk.model.dto.binding.xml.CardFromXmlDto;
import org.softuni.ruk.model.dto.binding.xml.CardsWrapperFromXmlDto;
import org.softuni.ruk.parser.DataImporter;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.persistance.service.api.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class CardController {

    private final CardService service;
    private final DataImporter importer;
    private final Parser parser;

    @Autowired
    public CardController(final CardService service,
                          final DataImporter importer,
                          @Qualifier("XMLParser") final Parser parser) {
        this.service = service;
        this.importer = importer;
        this.parser = parser;
    }

    public String importFromXML(String xmlContent) {
        CardsWrapperFromXmlDto entries = parser.read(CardsWrapperFromXmlDto.class, xmlContent);
        return this.importer.importData(
                entries.getCards().toArray(new CardFromXmlDto[0]),
                this.service);
    }
}
