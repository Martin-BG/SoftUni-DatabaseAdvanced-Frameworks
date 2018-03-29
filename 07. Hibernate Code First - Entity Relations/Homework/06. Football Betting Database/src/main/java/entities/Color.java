package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "colors")
public class Color implements Serializable {

    private static final long serialVersionUID = 333666999799111L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, length = 20, nullable = false)
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
