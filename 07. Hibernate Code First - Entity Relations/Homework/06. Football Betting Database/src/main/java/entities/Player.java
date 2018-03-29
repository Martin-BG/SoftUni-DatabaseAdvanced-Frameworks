package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "players")
public class Player implements Serializable {

    private static final long serialVersionUID = 333163265789235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(name = "squad_number")
    private short squadNumber;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Position position;

    @Column(name = "injured")
    private boolean isInjured;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getSquadNumber() {
        return this.squadNumber;
    }

    public void setSquadNumber(short squadNumber) {
        this.squadNumber = squadNumber;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isInjured() {
        return this.isInjured;
    }

    public void setInjured(boolean injured) {
        isInjured = injured;
    }
}
