package app.controller.impl;

import app.controller.MappingUtil;
import app.model.dto.cameras.CameraImportJsonDto;
import app.model.entities.BasicCamera;
import app.model.entities.DSLRCamera;
import app.model.entities.MirrorlessCamera;
import app.parser.api.Serializer;
import app.service.api.CamerasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CamerasController {
    private final String CAMERAS_XML = "E:\\Softuni\\SVN\\Databases\\Java Db Fundamentals 09-2017\\Database-Frameworks\\16.DB-Advanced-Exam-Preparation-1\\Submission_4948640 (1)\\src\\main\\resources\\files\\json\\cameras.json";
    private Serializer jsonSerializer;
    private CamerasService camerasService;

    @Autowired
    public CamerasController(@Qualifier(value = "JSONParser") Serializer jsonSerializer, CamerasService camerasService) {
        this.jsonSerializer = jsonSerializer;
        this.camerasService = camerasService;
    }

    public void importJSON() throws IOException, JAXBException {
        CameraImportJsonDto[] cameraDtos = jsonSerializer.deserialize(CameraImportJsonDto[].class, CAMERAS_XML);
        for (CameraImportJsonDto cameraDto : cameraDtos) {
            BasicCamera basicCamera = null;
            if ("DSLR".equals(cameraDto.getType())) {
                basicCamera = MappingUtil.convert(cameraDto, DSLRCamera.class);
                camerasService.add(basicCamera);
            } else if ("Mirrorless".equals(cameraDto.getType())) {
                basicCamera = MappingUtil.convert(cameraDto, MirrorlessCamera.class);
                camerasService.add(basicCamera);
            }

        }
    }
}
