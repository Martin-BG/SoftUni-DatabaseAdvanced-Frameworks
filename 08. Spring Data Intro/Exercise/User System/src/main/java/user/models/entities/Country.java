package user.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    private long id;
    private String name;
    private Set<Town> towns;

    public Country() {
        this.towns = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @OneToMany()
    @JoinColumn(referencedColumnName = "id")
    public Set<Town> getTowns() {
        return this.towns;
    }

    public void setTowns(final Set<Town> towns) {
        this.towns = towns;
    }
}
