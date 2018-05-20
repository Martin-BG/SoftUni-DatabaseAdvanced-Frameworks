package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PersonExportJSONDto implements Serializable {

    @Expose
    private String name;

    @Expose
    private PlanetExportJSONDto homePlanet;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlanetExportJSONDto getHomePlanet() {
        return this.homePlanet;
    }

    public void setHomePlanet(PlanetExportJSONDto homePlanet) {
        this.homePlanet = homePlanet;
    }
}
