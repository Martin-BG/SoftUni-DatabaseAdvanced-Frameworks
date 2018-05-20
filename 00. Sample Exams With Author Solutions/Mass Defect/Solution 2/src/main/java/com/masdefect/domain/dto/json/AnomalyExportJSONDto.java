package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AnomalyExportJSONDto implements Serializable {

    @Expose
    private long id;

    @Expose
    private PlanetExportJSONDto originPlanet;

    @Expose
    private PlanetExportJSONDto teleportPlanet;

    @Expose
    private long victimsCount;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PlanetExportJSONDto getOriginPlanet() {
        return this.originPlanet;
    }

    public void setOriginPlanet(PlanetExportJSONDto originPlanet) {
        this.originPlanet = originPlanet;
    }

    public PlanetExportJSONDto getTeleportPlanet() {
        return this.teleportPlanet;
    }

    public void setTeleportPlanet(PlanetExportJSONDto teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public long getVictimsCount() {
        return this.victimsCount;
    }

    public void setVictimsCount(long victimsCount) {
        this.victimsCount = victimsCount;
    }
}
