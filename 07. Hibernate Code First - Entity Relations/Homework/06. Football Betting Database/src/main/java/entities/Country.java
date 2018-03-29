package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country implements Serializable {

    private static final long serialVersionUID = 166666999789235L;

    @Id
    @Column(unique = true, nullable = false, length = 3)
    private String id;

    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents",
            joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id"))
    private Set<Continent> continents;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Continent> getContinents() {
        return this.continents;
    }

    public void setContinents(Set<Continent> continents) {
        this.continents = continents;
    }
}
