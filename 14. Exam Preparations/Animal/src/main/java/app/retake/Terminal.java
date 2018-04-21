package app.retake;

import app.retake.controllers.AnimalAidController;
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

    @Autowired
    public Terminal(final FileIO fileIO,
                    final ConsoleIO consoleIO,
                    final AnimalAidController animalAidController) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.animalAidController = animalAidController;
    }

    @Override
    public void run(String... strings) {
        consoleIO.write(this.animalAidController
                .importDataFromJSON(fileIO.read(Config.ANIMAL_AIDS_IMPORT_JSON)));
    }
}
