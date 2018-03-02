package bank.model;

import bank.contracts.Accountable;

public class BankAccount implements Accountable {

    private static final String ACCOUNT_ID_PREFIX = "ID";
    private static final double ZERO = 0d;

    private final int id;
    private double balance;

    public BankAccount(int id) {
        this.id = id;
        this.balance = ZERO;
    }

    private int getId() {
        return this.id;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    private void setBalance(double balance) {
        if (balance >= ZERO) {
            this.balance = balance;
        }
    }

    @Override
    public void deposit(double amount) {
        if (amount > ZERO) {
            this.setBalance(this.getBalance() + amount);
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > ZERO && this.getBalance() >= amount) {
            this.setBalance(this.getBalance() - amount);
        }
    }

    @Override
    public String toString() {
        return ACCOUNT_ID_PREFIX + this.getId();
    }
}
