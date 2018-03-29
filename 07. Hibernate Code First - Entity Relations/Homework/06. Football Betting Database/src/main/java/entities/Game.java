package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "games")
public class Game implements Serializable {

    private static final long serialVersionUID = 3336665934534135L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "home_team")
    private Team homeTeam;

    @OneToOne
    @JoinColumn(name = "away_team")
    private Team awayTeam;

    @Column(name = "home_goals")
    private short homeGoals;

    @Column(name = "away_goals")
    private short awayGoals;

    @Column(name = "date")
    private Date dateAndTime;

    @Column(name = "home_team_win_bet_rate")
    private double homeTeamWinBetRate;

    @Column(name = "away_team_win_bet_rate")
    private double awayTeamWinBetRate;

    @Column(name = "draw_game_bet_rate")
    private double drawGameBetRate;

    @ManyToOne
    private Round round;

    @ManyToOne
    private Competition competition;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return this.homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return this.awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public short getHomeGoals() {
        return this.homeGoals;
    }

    public void setHomeGoals(short homeGoals) {
        this.homeGoals = homeGoals;
    }

    public short getAwayGoals() {
        return this.awayGoals;
    }

    public void setAwayGoals(short awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Date getDateAndTime() {
        return this.dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public double getHomeTeamWinBetRate() {
        return this.homeTeamWinBetRate;
    }

    public void setHomeTeamWinBetRate(double homeTeamWinBetRate) {
        this.homeTeamWinBetRate = homeTeamWinBetRate;
    }

    public double getAwayTeamWinBetRate() {
        return this.awayTeamWinBetRate;
    }

    public void setAwayTeamWinBetRate(double awayTeamWinBetRate) {
        this.awayTeamWinBetRate = awayTeamWinBetRate;
    }

    public double getDrawGameBetRate() {
        return this.drawGameBetRate;
    }

    public void setDrawGameBetRate(double drawGameBetRate) {
        this.drawGameBetRate = drawGameBetRate;
    }

    public Round getRound() {
        return this.round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Competition getCompetition() {
        return this.competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
