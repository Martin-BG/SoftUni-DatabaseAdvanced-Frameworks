package app.retake.controllers;

import app.retake.domain.dto.ProcedureWrapperXMLImportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ProcedureController {

    private final ProcedureService procedureService;
    private final Parser parser;
    private final Importer importer;

    public ProcedureController(final ProcedureService procedureService,
                               @Qualifier("XMLParser") final Parser parser,
                               final Importer importer) {
        this.procedureService = procedureService;
        this.parser = parser;
        this.importer = importer;
    }

    public String importDataFromXML(String xmlContent) {
        try {
            final ProcedureWrapperXMLImportDTO procsDto = this.parser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);
            return this.importer.persist(procsDto.getProcedures().toArray(new ProcedureXMLImportDTO[0]), this.procedureService);
        } catch (JAXBException | IOException e) {
            return e.toString();
        }
    }

    public String exportProcedures() {
        return null;
    }
}
