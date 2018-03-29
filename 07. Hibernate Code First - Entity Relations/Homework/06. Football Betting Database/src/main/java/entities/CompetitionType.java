package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "competition_types")
public class CompetitionType implements Serializable {

    private static final long serialVersionUID = 297823899789235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20)
    private String name;

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
}
