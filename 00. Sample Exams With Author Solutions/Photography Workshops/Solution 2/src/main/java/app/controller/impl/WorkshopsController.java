package app.controller.impl;

import app.controller.MappingUtil;
import app.model.dto.workshops.WorkshopImportXmlDto;
import app.model.dto.workshops.WorkshopsImportXmlDto;
import app.model.entities.Workshop;
import app.parser.api.Serializer;
import app.service.api.WorkshopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class WorkshopsController {
    private final String WORKSHOPS_INPUT_XML = "E:\\Softuni\\SVN\\Databases\\Java Db Fundamentals 09-2017\\Database-Frameworks\\16.DB-Advanced-Exam-Preparation-1\\Submission_4948640 (1)\\src\\main\\resources\\files\\xml\\input\\workshops.xml";
    private Serializer xmlSerializer;
    private WorkshopsService workshopsService;

    @Autowired
    public WorkshopsController(@Qualifier(value = "XMLParser") Serializer xmlSerializer,
                               WorkshopsService workshopsService) {
        this.xmlSerializer = xmlSerializer;
        this.workshopsService = workshopsService;
    }

    public void importXML() throws IOException, JAXBException {
        WorkshopsImportXmlDto workshopsDto = xmlSerializer.deserialize(WorkshopsImportXmlDto.class, WORKSHOPS_INPUT_XML);
        for (WorkshopImportXmlDto workshopImportXmlDto : workshopsDto.getWorkshopImportXmlDtos()) {
            Workshop workshop = MappingUtil.convert(workshopImportXmlDto, Workshop.class);
            workshopsService.addWorkshop(workshop);
        }
    }
}
