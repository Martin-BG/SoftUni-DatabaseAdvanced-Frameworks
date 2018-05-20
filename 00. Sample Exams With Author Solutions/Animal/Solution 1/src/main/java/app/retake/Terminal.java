package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.api.ConsoleIO;
import app.retake.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private final AnimalAidController animalAidController;
    private final AnimalController animalController;
    private final VetController vetController;
    private final ProcedureController procedureController;
    private final FileIO fileIO;
    private final ConsoleIO consoleIO;

    @Autowired
    public Terminal(AnimalAidController animalAidController,
                    AnimalController animalController,
                    VetController vetController,
                    ProcedureController procedureController,
                    FileIO fileIO,
                    ConsoleIO consoleIO) {
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.vetController = vetController;
        this.procedureController = procedureController;
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.consoleIO.write(this.animalAidController
                .importDataFromJSON(
                        this.fileIO
                                .read(Config.ANIMAL_AIDS_IMPORT_JSON)));
        this.consoleIO.write(this.animalController
                .importDataFromJSON(
                        this.fileIO
                                .read(Config.ANIMALS_IMPORT_JSON)));
        this.consoleIO.write(this.vetController
                .importDataFromXML(
                        this.fileIO
                                .read(Config.VETS_IMPORT_XML)));
        this.consoleIO.write(this.procedureController
                .importDataFromXML(
                        this.fileIO
                                .read(Config.PROCEDURES_IMPORT_XML)));

        this.fileIO.write(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"), "exportAnimalsByOwnerPhoneNumber.json");
        this.fileIO.write(this.procedureController.exportProcedures(), "exportProcedures.xml");

    }
}
