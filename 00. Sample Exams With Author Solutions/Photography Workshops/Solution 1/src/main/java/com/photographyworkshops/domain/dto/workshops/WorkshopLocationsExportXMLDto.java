package com.photographyworkshops.domain.dto.workshops;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "locations")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopLocationsExportXMLDto {

    @XmlElement(name = "location")
    private List<WorkshopLocationExportXMLDto> locations;

    public WorkshopLocationsExportXMLDto() {
        this.setLocations(new ArrayList<>());
    }

    public List<WorkshopLocationExportXMLDto> getLocations() {
        return this.locations;
    }

    public void setLocations(List<WorkshopLocationExportXMLDto> locations) {
        this.locations = locations;
    }
}
