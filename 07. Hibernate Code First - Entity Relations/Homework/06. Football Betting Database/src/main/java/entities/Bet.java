package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bets")
public class Bet implements Serializable {

    private static final long serialVersionUID = 322386999789235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "bet_money")
    private BigDecimal betMoney;

    @Column(name = "date_and_time")
    private Date dateAndTimeOfBet;

    @ManyToOne
    private User user;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBetMoney() {
        return this.betMoney;
    }

    public void setBetMoney(BigDecimal betMoney) {
        this.betMoney = betMoney;
    }

    public Date getDateAndTimeOfBet() {
        return this.dateAndTimeOfBet;
    }

    public void setDateAndTimeOfBet(Date dateAndTimeOfBet) {
        this.dateAndTimeOfBet = dateAndTimeOfBet;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
