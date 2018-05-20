package com.masdefect.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "planets")
public class Planet implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sun_id")
    private Star sun;

    @ManyToOne
    @JoinColumn(name = "solar_system_id")
    private SolarSystem solarSystem;

    @NotNull
    @OneToMany(mappedBy = "originPlanet", fetch = FetchType.EAGER)
    private Set<Anomaly> isOriginPlanetTo;

    @NotNull
    @OneToMany(mappedBy = "teleportPlanet", fetch = FetchType.EAGER)
    private Set<Anomaly> isTeleportPlanetTo;

    @OneToMany(mappedBy = "homePlanet", fetch = FetchType.EAGER)
    private Set<Person> people;

    public Planet() {
        this.setIsOriginPlanetTo(new HashSet<>());
        this.setIsTeleportPlanetTo(new HashSet<>());
        this.setPeople(new HashSet<>());
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

    public Star getSun() {
        return this.sun;
    }

    public void setSun(Star sun) {
        this.sun = sun;
    }

    public SolarSystem getSolarSystem() {
        return this.solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public Set<Anomaly> getIsOriginPlanetTo() {
        return isOriginPlanetTo;
    }

    public void setIsOriginPlanetTo(Set<Anomaly> isOriginPlanetTo) {
        this.isOriginPlanetTo = isOriginPlanetTo;
    }

    public Set<Anomaly> getIsTeleportPlanetTo() {
        return isTeleportPlanetTo;
    }

    public void setIsTeleportPlanetTo(Set<Anomaly> isTeleportPlanetTo) {
        this.isTeleportPlanetTo = isTeleportPlanetTo;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }
}
