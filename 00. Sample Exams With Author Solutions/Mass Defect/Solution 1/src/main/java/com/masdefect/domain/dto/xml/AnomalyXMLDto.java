package com.masdefect.domain.dto.xml;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "anomaly")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomalyXMLDto {

    @XmlAttribute(name = "origin-homePlanet")
    private String originPlanetName;

    @XmlAttribute(name = "teleport-homePlanet")
    private String teleportPlanetName;

    @XmlElementWrapper(name = "victims")
    @XmlElement(name = "victim")
    private Set<VictimXMLDto> persons;

    public AnomalyXMLDto() {
        this.setPersons(new HashSet<>());
    }

    public String getOriginPlanetName() {
        return this.originPlanetName;
    }

    public void setOriginPlanetName(String originPlanetName) {
        this.originPlanetName = originPlanetName;
    }

    public String getTeleportPlanetName() {
        return this.teleportPlanetName;
    }

    public void setTeleportPlanetName(String teleportPlanetName) {
        this.teleportPlanetName = teleportPlanetName;
    }

    public Set<VictimXMLDto> getPersons() {
        return this.persons;
    }

    public void setPersons(Set<VictimXMLDto> persons) {
        this.persons = persons;
    }
}
