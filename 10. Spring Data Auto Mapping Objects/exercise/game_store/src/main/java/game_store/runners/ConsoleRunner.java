package game_store.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.time.format.DateTimeFormatter;

@Controller
public class ConsoleRunner implements CommandLineRunner {

    private static final String INPUT_SEPARATOR = "\\s+";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");

    @Autowired
    public ConsoleRunner() {

    }

    @Override
    public void run(final String... args) {


    }
}
