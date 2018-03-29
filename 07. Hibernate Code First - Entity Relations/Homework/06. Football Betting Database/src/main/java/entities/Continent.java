package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent implements Serializable {

    private static final long serialVersionUID = 311166721189235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy = "continents")
    private Set<Country> countries;

    public Set<Country> getCountries() {
        return this.countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

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
