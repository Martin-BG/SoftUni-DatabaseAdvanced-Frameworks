package bank.controller;

import bank.contracts.InputReader;
import bank.contracts.OutputWriter;
import bank.model.Bank;

public class BankController {

    private static final String INVALID_COMMAND = "Invalid command!";
    private static final String END = "END";
    private static final String CREATE = "CREATE";
    private static final String DEPOSIT = "DEPOSIT";
    private static final String WITHDRAW = "WITHDRAW";
    private static final String PRINT = "PRINT";
    private static final String GET_INTEREST = "GETINTEREST";
    private static final int COMMAND_INDEX = 0;
    private static final int ACCOUNT_INDEX = 1;
    private static final int INTEREST_INDEX = 1;
    private static final int AMOUNT_INDEX = 2;
    private static final int YEARS_INDEX = 2;
    private static final String TOKENS_SEPARATOR = "\\s+";
    private static final String SET_INTEREST = "SETINTEREST";

    private final Bank bank;
    private final OutputWriter writer;
    private final InputReader reader;

    public BankController(Bank bank, OutputWriter writer, InputReader reader) {
        this.bank = bank;
        this.writer = writer;
        this.reader = reader;
    }

    public void run() {
        while (true) {

            String[] tokens = this.reader.readLine().toUpperCase().split(TOKENS_SEPARATOR);

            String command = tokens[COMMAND_INDEX];

            if (END.equals(command)) {
                break;
            }

            int id = tokens.length > ACCOUNT_INDEX ? Integer.parseInt(tokens[ACCOUNT_INDEX]) : -1;
            double amount = tokens.length > AMOUNT_INDEX ? Double.parseDouble(tokens[AMOUNT_INDEX]) : 0d;

            String result = null;

            switch (command) {
            case CREATE:
                result = this.bank.createAccount(id);
                break;
            case DEPOSIT:
                result = this.bank.depositToAccount(id, amount);
                break;
            case WITHDRAW:
                result = this.bank.withdrawFromAccount(id, amount);
                break;
            case PRINT:
                result = this.bank.getAccountDetails(id);
                break;
            case SET_INTEREST:
                this.bank.setInterestRate(Double.parseDouble(tokens[INTEREST_INDEX]));
                break;
            case GET_INTEREST:
                result = this.bank.getInterestForAccount(id, Integer.parseInt(tokens[YEARS_INDEX]));
                break;
            default:
                result = INVALID_COMMAND;
                break;
            }

            this.writer.println(result);
        }
    }
}
