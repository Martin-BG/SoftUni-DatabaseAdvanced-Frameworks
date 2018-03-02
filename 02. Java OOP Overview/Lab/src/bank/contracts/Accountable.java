package bank.contracts;

public interface Accountable {

    double getBalance();

    void deposit(double amount);

    void withdraw(double amount);
}
