package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlanetImportJSONDto implements Serializable {

    @Expose
    private String name;

    @SerializedName(value = "sun")
    @Expose
    private String sunName;

    @SerializedName(value = "solarSystem")
    @Expose
    private String solarSystemName;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSunName() {
        return this.sunName;
    }

    public void setSunName(String sunName) {
        this.sunName = sunName;
    }

    public String getSolarSystemName() {
        return this.solarSystemName;
    }

    public void setSolarSystemName(String solarSystemName) {
        this.solarSystemName = solarSystemName;
    }
}
