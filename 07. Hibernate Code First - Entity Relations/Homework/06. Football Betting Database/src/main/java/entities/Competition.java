package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "competitions")
public class Competition implements Serializable {

    private static final long serialVersionUID = 331927531119235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20)
    private String name;

    @ManyToOne
    private CompetitionType type;

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

    public CompetitionType getType() {
        return this.type;
    }

    public void setType(CompetitionType type) {
        this.type = type;
    }
}
