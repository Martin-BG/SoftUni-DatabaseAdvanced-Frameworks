package bank.model;

import bank.contracts.Accountable;
import bank.factories.BankAccountFactory;

import java.util.Map;

public final class Bank {

    private static final String ACCOUNT_DOES_NOT_EXIST = "Account does not exist";
    private static final String ACCOUNT_DETAILS = "Account %s, balance %.2f%n";
    private static final String INSUFFICIENT_BALANCE = "Insufficient balance";
    private static final String ACCOUNT_ALREADY_EXISTS = "Account already exists";
    private static final String ACCOUNT_CREATED = "Account %s created";
    private static final String DEPOSITED_TO_ACCOUNT = "Deposited %.2f to %s";
    private static final String WITHDRAWN_FROM_ACCOUNT = "Withdrawn %.2f from %s";
    private static final String INTEREST_FORMAT = "Interest for the given period: %.2f";

    private static final double DEFAULT_INTEREST_RATE = 0.02;
    private static final double ZERO = 0d;

    private static int bankAccountsCount = 0;

    private final String name;
    private final Map<Integer, Accountable> accounts;
    private final BankAccountFactory accountFactory;
    private double interestRate = DEFAULT_INTEREST_RATE;

    public Bank(String name, Map<Integer, Accountable> accounts, BankAccountFactory accountFactory) {
        this.accounts = accounts;
        this.name = name;
        this.accountFactory = accountFactory;
    }

    public String getName() {
        return this.name;
    }

    public String getAccountDetails(int id) {
        Accountable account = getAccount(id);

        if (account == null) {
            return ACCOUNT_DOES_NOT_EXIST;
        }

        return String.format(ACCOUNT_DETAILS, account, account.getBalance());
    }

    private Accountable getAccount(int id) {
        return this.accounts.get(id);
    }

    private Accountable addAccount(int id) {
        Accountable account = getAccount(id);

        if (account == null) {

            if (id < 0) {
                id = ++bankAccountsCount;
            }

            account = this.accountFactory.create(id);
            this.accounts.put(id, account);
        }

        return account;
    }

    public String withdrawFromAccount(int id, double amount) {
        Accountable account = this.getAccount(id);

        if (account == null) {
            return ACCOUNT_DOES_NOT_EXIST;
        }

        if (amount > ZERO && amount > account.getBalance()) {
            return INSUFFICIENT_BALANCE;
        }

        account.withdraw(amount);

        return String.format(WITHDRAWN_FROM_ACCOUNT, amount, account);
    }

    public String depositToAccount(int id, double amount) {
        Accountable account = this.getAccount(id);

        if (account == null) {
            return ACCOUNT_DOES_NOT_EXIST;
        }

        if (amount > ZERO) {
            account.deposit(amount);
            return String.format(DEPOSITED_TO_ACCOUNT, amount, account);
        }

        return null;
    }

    public String createAccount(int id) {
        Accountable account = this.getAccount(id);

        if (account != null) {
            return ACCOUNT_ALREADY_EXISTS;
        }

        account = this.addAccount(id);

        return String.format(ACCOUNT_CREATED, account);
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interest) {
        this.interestRate = interest;
    }

    public String getInterestForAccount(int id, int years) {
        Accountable account = this.getAccount(id);

        if (account == null) {
            return ACCOUNT_DOES_NOT_EXIST;
        }

        return String.format(INTEREST_FORMAT, account.getBalance() * this.getInterestRate() * years);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
