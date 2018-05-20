package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PersonImportJSONDto implements Serializable {

    @Expose
    private String name;

    @SerializedName(value = "homePlanet")
    @Expose
    private String homePlanetName;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomePlanetName() {
        return this.homePlanetName;
    }

    public void setHomePlanetName(String homePlanetName) {
        this.homePlanetName = homePlanetName;
    }
}
