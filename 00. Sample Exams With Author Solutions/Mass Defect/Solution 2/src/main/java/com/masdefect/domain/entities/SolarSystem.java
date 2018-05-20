package com.masdefect.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "solar_systems")
public class SolarSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "solarSystem")
    private Set<Star> starSet;

    @OneToMany(mappedBy = "solarSystem")
    private Set<Planet> planets;

    public SolarSystem() {
        this.setPlanets(new HashSet<>());
        this.setStarSet(new HashSet<>());
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

    public Set<Star> getStarSet() {
        return starSet;
    }

    public Set<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(Set<Planet> planets) {
        this.planets = planets;
    }

    public void setStarSet(Set<Star> starSet) {
        this.starSet = starSet;
    }
}
