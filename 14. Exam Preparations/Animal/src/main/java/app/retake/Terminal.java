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

    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final AnimalAidController animalAidController;
    private final AnimalController animalController;
    private final VetController vetController;
    private final ProcedureController procedureController;

    @Autowired
    public Terminal(final FileIO fileIO,
                    final ConsoleIO consoleIO,
                    final AnimalAidController animalAidController,
                    final AnimalController animalController,
                    final VetController vetController,
                    final ProcedureController procedureController) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.vetController = vetController;
        this.procedureController = procedureController;
    }

    @Override
    public void run(String... strings) {
        consoleIO.write(this.animalAidController
                .importDataFromJSON(fileIO.read(Config.ANIMAL_AIDS_IMPORT_JSON)));

        consoleIO.write(this.animalController
                .importDataFromJSON(fileIO.read(Config.ANIMALS_IMPORT_JSON)));

        consoleIO.write(this.vetController
                .importDataFromXML(fileIO.read(Config.VETS_IMPORT_XML)));

        consoleIO.write(this.procedureController
                .importDataFromXML(fileIO.read(Config.PROCEDURES_IMPORT_XML)));

        consoleIO.write(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"));
    }
}
