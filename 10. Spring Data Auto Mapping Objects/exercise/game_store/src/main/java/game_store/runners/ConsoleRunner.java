package game_store.runners;

import game_store.commands.CommandFactory;
import game_store.commands.Executable;
import game_store.constants.CommandConstants;
import game_store.constants.CommandMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Controller
public class ConsoleRunner implements CommandLineRunner {

    private final CommandFactory commandFactory;

    @Autowired
    public ConsoleRunner(final CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public void run(final String... args) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            while (true) {
                String input = reader.readLine();

                if (input == null) {
                    continue;
                }

                input = input.trim();

                if (CommandConstants.END_COMMAND.equalsIgnoreCase(input)) {
                    break;
                }

                String[] tokens = input.split(CommandConstants.INPUT_COMMANDS_SEPARATOR);

                String cmd = tokens[0];

                String[] params = Arrays.copyOfRange(tokens, 1, tokens.length);

                Executable command = this.commandFactory.getCommand(cmd);

                if (command != null) {
                    try {
                        System.out.println(command.execute(params));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println(CommandMessages.INVALID_COMMAND);
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
