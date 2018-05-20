package com.photographyworkshops.terminal;

import com.photographyworkshops.config.Config;
import com.photographyworkshops.domain.dto.accessoaries.AccessoriesImportXMLDto;
import com.photographyworkshops.domain.dto.accessoaries.AccessoryImportXMLDto;
import com.photographyworkshops.domain.dto.cameras.BasicCameraImportJSONDto;
import com.photographyworkshops.domain.dto.lens.LenImportJSONDto;
import com.photographyworkshops.domain.dto.photographers.PhotographerExportJSONDto;
import com.photographyworkshops.domain.dto.photographers.PhotographerImportJSONDto;
import com.photographyworkshops.domain.dto.photographers.PhotographerLandscapeExportJSONDto;
import com.photographyworkshops.domain.dto.photographers.PhotographersCameraExportXML;
import com.photographyworkshops.domain.dto.workshops.WorkshopImportXMLDto;
import com.photographyworkshops.domain.dto.workshops.WorkshopLocationsExportXMLDto;
import com.photographyworkshops.domain.dto.workshops.WorkshopsImportXMLDto;
import com.photographyworkshops.domain.entities.workshops.Workshop;
import com.photographyworkshops.io.interfaces.ConsoleIO;
import com.photographyworkshops.parser.interfaces.FileParser;
import com.photographyworkshops.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    @Qualifier(value = "JSONParser")
    private FileParser jsonParser;

    @Autowired
    @Qualifier(value = "XMLParser")
    private FileParser xmlParser;

    @Autowired
    private ConsoleIO consoleIO;

    @Autowired
    private LenService lenService;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private PhotographerService photographerService;

    @Autowired
    private AccessoryService accessoryService;

    @Autowired
    private WorkshopService workshopService;

    @Override
    public void run(String... strings) throws Exception {
        //Import
        this.importLensFromJson();
        this.importCamerasFromJson();
        this.importPhotographersFromJson();
        this.importAccesoriesFromXML();
        this.importWorkshopsFromXML();
        //Export
        this.exportPhotographersToJSON();
        this.exportPhotographersLandscapeToJSON();
        this.exportPhotographersCamerasToXML();
        this.exportWorkshopsToXML();
    }

    private void importLensFromJson() {
        LenImportJSONDto[] lenDtos = null;
        try {
            lenDtos = this.jsonParser.read(LenImportJSONDto[].class, Config.LENS_IMPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (LenImportJSONDto lenDto : lenDtos) {
            try {
                this.lenService.create(lenDto);
                this.consoleIO.write(String.format("Successfully imported %s %dmm f%.2f", lenDto.getMake(), lenDto.getFocalLength(), lenDto.getMaxAperture()));
            } catch (Exception e) {
                this.consoleIO.write("Error. Invalid data provided");
            }
        }
    }

    private void importCamerasFromJson() {
        BasicCameraImportJSONDto[] basicCameraDtos = null;
        try {
            basicCameraDtos = this.jsonParser.read(BasicCameraImportJSONDto[].class, Config.CAMERAS_IMPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (BasicCameraImportJSONDto basicCameraDto : basicCameraDtos) {
            try {
                this.cameraService.create(basicCameraDto);
                this.consoleIO.write(String.format("Successfully imported %s %s %s", basicCameraDto.getType(), basicCameraDto.getMake(), basicCameraDto.getModel()));
            } catch (Exception e) {
                this.consoleIO.write("Error. Invalid data provided");
            }
        }
    }

    private void importPhotographersFromJson() {
        PhotographerImportJSONDto[] photographerDtos = null;
        try {
            photographerDtos = this.jsonParser.read(PhotographerImportJSONDto[].class, Config.PHOTOGRAPHERS_IMPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (PhotographerImportJSONDto photographerDto : photographerDtos) {
            try {
                int size = this.photographerService.create(photographerDto);
                this.consoleIO.write(String.format("Successfully imported %s %s | Lenses: %d", photographerDto.getFirstName(), photographerDto.getLastName(), size));
            } catch (Exception e) {
                this.consoleIO.write("Error. Invalid data provided");
            }
        }
    }

    private void importAccesoriesFromXML() {
        AccessoriesImportXMLDto accessoriesDto = null;
        try {
            accessoriesDto = this.xmlParser.read(AccessoriesImportXMLDto.class, Config.ACCESSOARIES_IMPORT_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (AccessoryImportXMLDto accessoryDto : accessoriesDto.getAccessories()) {
            try {
                this.accessoryService.create(accessoryDto);
                this.consoleIO.write(String.format("Successfully imported %s", accessoryDto.getName()));
            } catch (Exception e) {
                this.consoleIO.write("Error. Invalid data provided");
            }
        }
    }

    private void importWorkshopsFromXML() {
        WorkshopsImportXMLDto workshopsImportXMLDto = null;
        try {
            workshopsImportXMLDto = this.xmlParser.read(WorkshopsImportXMLDto.class, Config.WORKSHOPS_IMPORT_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (WorkshopImportXMLDto workshopImportXMLDto : workshopsImportXMLDto.getWorkshops()) {
            try {
                this.workshopService.create(workshopImportXMLDto);
                this.consoleIO.write(String.format("Successfully imported %s", workshopImportXMLDto.getName()));
            } catch (Exception e) {
                this.consoleIO.write("Error. Invalid data provided");
            }
        }
    }

    private void exportPhotographersToJSON() {
        List<PhotographerExportJSONDto> exportJSONDtos = this.photographerService.findAllOrder();
        try {
            this.jsonParser.write(exportJSONDtos, Config.PHOTOGRAPHERS_EXPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportPhotographersLandscapeToJSON() {
        List<PhotographerLandscapeExportJSONDto> landscapeExportJSONDtos = this.photographerService.findAllWithLenses();
        try {
            this.jsonParser.write(landscapeExportJSONDtos, Config.PHOTOGRAPHERS_LANDCSCAPE_EXPORT_JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportPhotographersCamerasToXML() {
        PhotographersCameraExportXML photographersCameraExportXML = this.photographerService.findAllWithCameras();
        try {
            this.xmlParser.write(photographersCameraExportXML, Config.PHOTOGRAPHERS_CAMERAS_EXPORT_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportWorkshopsToXML() {
        WorkshopLocationsExportXMLDto workshopLocationsExportXMLDto = this.workshopService.findAllByLocation();
        try {
            this.xmlParser.write(workshopLocationsExportXMLDto, Config.WORKSHOPS_EXPORT_XML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
