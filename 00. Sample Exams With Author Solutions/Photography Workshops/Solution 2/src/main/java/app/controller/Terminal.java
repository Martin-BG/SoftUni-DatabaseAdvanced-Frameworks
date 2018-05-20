package app.controller;


import app.controller.impl.AccessoriesController;
import app.controller.impl.CamerasController;
import app.controller.impl.PhotographersController;
import app.controller.impl.WorkshopsController;
import app.parser.api.Serializer;
import app.service.api.WorkshopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class Terminal implements CommandLineRunner {
    @Autowired
    @Qualifier(value = "JSONParser")
    private Serializer jsonSerializer;

    @Autowired
    @Qualifier(value = "XMLParser")
    private Serializer xmlSerializer;

    @Autowired
    private WorkshopsService workshopsService;

    @Autowired
    private CamerasController camerasController;

    @Autowired
    private AccessoriesController accessoriesController;

    @Autowired
    private PhotographersController photographersController;

    @Autowired
    private WorkshopsController workshopsController;

    @Override
    public void run(String... strings) throws Exception {
        camerasController.importJSON();
        photographersController.importJSON();
        accessoriesController.importXML();
        workshopsController.importXML();
        System.out.println(photographersController.exportPhotographersOrder());
        photographersController.exportPhotographersWithSameCameras();
//        exportWorkshopsByLocations();
    }
}
