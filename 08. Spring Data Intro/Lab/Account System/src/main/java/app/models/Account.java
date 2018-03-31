package app.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    private long id;
    private BigDecimal balance;
    private User user;

    public Account() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Column
    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(final BigDecimal balance) {
        if (BigDecimal.ZERO.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        this.balance = balance;
    }

    @ManyToOne(optional = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}
