package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnomalyImportJSONDto implements Serializable {

    @SerializedName(value = "teleportPlanet")
    @Expose
    private String teleportPlanetName;

    @SerializedName(value = "originPlanet")
    @Expose
    private String originPlanetName;

    public String getTeleportPlanetName() {
        return this.teleportPlanetName;
    }

    public void setTeleportPlanetName(String teleportPlanetName) {
        this.teleportPlanetName = teleportPlanetName;
    }

    public String getOriginPlanetName() {
        return this.originPlanetName;
    }

    public void setOriginPlanetName(String originPlanetName) {
        this.originPlanetName = originPlanetName;
    }
}
