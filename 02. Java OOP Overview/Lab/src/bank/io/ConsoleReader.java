package bank.io;

import bank.contracts.InputReader;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleReader implements InputReader {

    private final BufferedReader reader;

    public ConsoleReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
