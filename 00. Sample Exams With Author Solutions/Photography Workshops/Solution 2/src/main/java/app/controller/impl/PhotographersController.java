package app.controller.impl;

import app.controller.MappingUtil;
import app.model.dto.photographers.PhotographerImportJsonDto;
import app.model.dto.photographers.PhotographerXmlExpotDto;
import app.model.dto.photographers.PhotographersOrderedExportJson;
import app.model.dto.photographers.PhotographersXmlExportDot;
import app.model.entities.Photographer;
import app.parser.api.Serializer;
import app.service.api.PhotographersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class PhotographersController {

    private final String PHOTOGRAPHERS_INPUT_JSON = "E:\\Softuni\\SVN\\Databases\\Java Db Fundamentals 09-2017\\Database-Frameworks\\16.DB-Advanced-Exam-Preparation-1\\Submission_4948640 (1)\\src\\main\\resources\\files\\json\\photographers.json";
    private final String PHOTOGRAPHERS_ORDERED_OUTPUT_JSON = "files/exported/ordered.xml";
    private final String PHOTOGRAPHERS_SAME_CAMERAS_OUTPUT_XML = "E:\\Softuni\\SVN\\Databases\\Java Db Fundamentals 09-2017\\Database-Frameworks\\16.DB-Advanced-Exam-Preparation-1\\Submission_4948640 (1)\\src\\main\\resources\\files\\exported\\ordered.xml";


    private Serializer jsonSerializer;
    private Serializer xmlSerializer;
    private PhotographersService photographersService;

    @Autowired
    public PhotographersController(@Qualifier(value = "JSONParser") Serializer jsonSerializer,
                                   @Qualifier(value = "XMLParser") Serializer xmlSerializer,
                                   PhotographersService photographersService) {
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
        this.photographersService = photographersService;
    }

    public void importJSON() throws IOException, JAXBException {
        PhotographerImportJsonDto[] photographerDtos = jsonSerializer.deserialize(PhotographerImportJsonDto[].class, PHOTOGRAPHERS_INPUT_JSON);
        this.photographersService.createMany(photographerDtos);
    }

    public String exportPhotographersOrder() throws JAXBException, IOException {
        List<Photographer> allOrdered = photographersService.findAllOrdered();
        List<PhotographersOrderedExportJson> photographers = MappingUtil.convert(allOrdered, PhotographersOrderedExportJson.class);
        return jsonSerializer.serialize(photographers, PHOTOGRAPHERS_ORDERED_OUTPUT_JSON);
    }

    public String exportPhotographersWithSameCameras() throws JAXBException, IOException {
        List<Photographer> allWithSameCameras = photographersService.findAllWithSameCameras();
        List<PhotographerXmlExpotDto> convert = MappingUtil.convert(allWithSameCameras, PhotographerXmlExpotDto.class);
        PhotographersXmlExportDot photographersXmlExportDot = new PhotographersXmlExportDot();
        photographersXmlExportDot.setPhotographerXmlExpotDtos(convert);
        xmlSerializer.serialize(photographersXmlExportDot, PHOTOGRAPHERS_SAME_CAMERAS_OUTPUT_XML);
        return xmlSerializer.serialize(photographersXmlExportDot);
    }
}