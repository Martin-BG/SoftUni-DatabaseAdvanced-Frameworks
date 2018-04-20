package app.retake.controllers;

import app.retake.domain.dto.ProcedureWrapperXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ProcedureController {

    private final ProcedureService procedureService;
    private final Parser parser;

    @Autowired
    public ProcedureController(ProcedureService procedureService,
                               @Qualifier("XMLParser") Parser parser) {
        this.procedureService = procedureService;
        this.parser = parser;
    }

    public String importDataFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        try {
            ProcedureWrapperXMLImportDTO vets = parser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);
            vets.getProcedures().forEach(a -> {
                try {
                    this.procedureService.create(a);
                    sb.append("Record successfully imported.")
                            .append(System.lineSeparator());
                } catch (IllegalArgumentException ignored) {
                }

            });
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String exportProcedures() throws IOException, JAXBException {
        return this.parser.write(this.procedureService.exportProcedures());
    }
}
