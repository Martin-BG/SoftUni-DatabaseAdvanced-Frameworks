package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "towns")
public class Town implements Serializable {

    private static final long serialVersionUID = 331233223189235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @ManyToOne
    private Country country;

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

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
