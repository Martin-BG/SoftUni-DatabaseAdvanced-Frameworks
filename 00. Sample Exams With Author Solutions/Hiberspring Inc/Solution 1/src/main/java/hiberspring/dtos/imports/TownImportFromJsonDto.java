package hiberspring.dtos.imports;

import com.google.gson.annotations.Expose;

public class TownImportFromJsonDto {

    @Expose
    private String name;

    @Expose
    private Integer population;

    public TownImportFromJsonDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return this.population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
