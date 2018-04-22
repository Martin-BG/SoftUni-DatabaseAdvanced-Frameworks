package app.retake.controllers;

import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class VetController {

    private final VetService vetService;
    private final Parser parser;
    private final Importer importer;

    public VetController(final VetService vetService,
                         @Qualifier("XMLParser") final Parser parser,
                         final Importer importer) {
        this.vetService = vetService;
        this.parser = parser;
        this.importer = importer;
    }

    public String importDataFromXML(String xmlContent) {
        try {
            final VetWrapperXMLImportDTO vetsDto = this.parser.read(VetWrapperXMLImportDTO.class, xmlContent);
            return this.importer.persist(vetsDto.getVets().toArray(new VetXMLImportDTO[0]), this.vetService);
        } catch (JAXBException | IOException e) {
            return e.toString();
        }
    }
}
