package com.masdefect.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "planets")
public class Planet implements Serializable {

    @OneToMany(mappedBy = "teleportPlanet")
    Set<Anomaly> anomalies;
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

    public Planet() {
        this.setAnomalies(new HashSet<>());
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

    public Set<Anomaly> getAnomalies() {
        return this.anomalies;
    }

    public void setAnomalies(Set<Anomaly> anomalies) {
        this.anomalies = anomalies;
    }
}
