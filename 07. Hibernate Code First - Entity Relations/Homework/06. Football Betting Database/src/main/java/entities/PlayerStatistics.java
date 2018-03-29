package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "players_statistics")
public class PlayerStatistics implements Serializable {

    private static final long serialVersionUID = 33366699912345L;

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "goals")
    private short scoredGoals;

    @Column(name = "assists")
    private short playerAssists;

    @Column(name = "minutes_played")
    private short minutesPlayed;

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public short getScoredGoals() {
        return this.scoredGoals;
    }

    public void setScoredGoals(short scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public short getPlayerAssists() {
        return this.playerAssists;
    }

    public void setPlayerAssists(short playerAssists) {
        this.playerAssists = playerAssists;
    }

    public short getMinutesPlayed() {
        return this.minutesPlayed;
    }

    public void setMinutesPlayed(short minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlayerStatistics that = (PlayerStatistics) o;

        if (scoredGoals != that.scoredGoals) {
            return false;
        }
        if (playerAssists != that.playerAssists) {
            return false;
        }
        if (minutesPlayed != that.minutesPlayed) {
            return false;
        }
        if (game != null ? !game.equals(that.game) : that.game != null) {
            return false;
        }
        return player != null ? player.equals(that.player) : that.player == null;
    }

    @Override
    public int hashCode() {
        int result = game != null ? game.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (int) scoredGoals;
        result = 31 * result + (int) playerAssists;
        result = 31 * result + (int) minutesPlayed;
        return result;
    }
}
