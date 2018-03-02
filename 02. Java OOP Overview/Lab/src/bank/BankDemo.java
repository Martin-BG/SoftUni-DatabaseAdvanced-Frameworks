package bank;

import bank.contracts.InputReader;
import bank.contracts.OutputWriter;
import bank.controller.BankController;
import bank.factories.BankAccountFactory;
import bank.io.ConsoleReader;
import bank.io.ConsoleWriter;
import bank.model.Bank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BankDemo {

    public static void main(String[] args) {

        final BankController bankController;

        {
            Bank bank = new Bank("The Bank",
                    new HashMap<>(),
                    BankAccountFactory.getInstance());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            InputReader reader = new ConsoleReader(bufferedReader);
            OutputWriter writer = new ConsoleWriter();

            bankController = new BankController(bank, writer, reader);
        }

        bankController.run();
    }
}
