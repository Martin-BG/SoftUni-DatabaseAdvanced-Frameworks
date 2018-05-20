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
    public AnomalyXMLDto() {

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
}
