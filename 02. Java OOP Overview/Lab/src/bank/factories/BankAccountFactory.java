package bank.factories;

import bank.model.BankAccount;

public class BankAccountFactory {

    private static final BankAccountFactory INSTANCE = new BankAccountFactory();

    private BankAccountFactory() {
    }

    public static BankAccountFactory getInstance() {
        return INSTANCE;
    }

    public BankAccount create(int id) {
        return new BankAccount(id);
    }
}
