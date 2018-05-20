package app.controller.impl;

import app.model.dto.accessories.AccessoriesXmlDto;
import app.parser.api.Serializer;
import app.service.api.AccessoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
@Component
public class AccessoriesController {
    private final static String ACCESSORIES_XML = "E:\\Softuni\\SVN\\Databases\\Java Db Fundamentals 09-2017\\Database-Frameworks\\16.DB-Advanced-Exam-Preparation-1\\Submission_4948640 (1)\\src\\main\\resources\\files\\xml\\input\\accessories.xml";
    private Serializer xmlSerializer;
    private AccessoriesService accessoriesService;

    @Autowired
    public AccessoriesController(@Qualifier(value = "XMLParser") Serializer xmlSerializer,
                                 AccessoriesService accessoriesService) {
        this.xmlSerializer = xmlSerializer;
        this.accessoriesService = accessoriesService;
    }

    public void importXML() throws IOException, JAXBException {
        AccessoriesXmlDto accessoriesDto = xmlSerializer.deserialize(AccessoriesXmlDto.class, ACCESSORIES_XML);
        accessoriesService.createMany(accessoriesDto.getAccessoryXMLDtos());
    }
}