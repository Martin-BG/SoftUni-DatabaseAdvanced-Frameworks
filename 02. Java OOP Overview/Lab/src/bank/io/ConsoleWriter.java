package bank.io;

import bank.contracts.OutputWriter;

public class ConsoleWriter implements OutputWriter {

    public ConsoleWriter() {
    }

    @Override
    public void println(String output) {
        if (output != null) {
            System.out.println(output);
        }
    }

    @Override
    public void print(String output) {
        if (output != null) {
            System.out.print(output);
        }
    }
}