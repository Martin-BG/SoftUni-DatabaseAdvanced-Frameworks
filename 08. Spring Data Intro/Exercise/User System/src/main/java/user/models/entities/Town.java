package user.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {

    private Long id;
    private String name;
    private Country country;
    private Set<User> born;
    private Set<User> live;

    public Town() {
        this.born = new HashSet<>();
        this.live = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @ManyToOne(optional = false)
    public Country getCountry() {
        return this.country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "bornTown")
    public Set<User> getBorn() {
        return this.born;
    }

    public void setBorn(final Set<User> born) {
        this.born = born;
    }

    @OneToMany(mappedBy = "currentlyLiving")
    public Set<User> getLive() {
        return this.live;
    }

    public void setLive(final Set<User> live) {
        this.live = live;
    }
}
