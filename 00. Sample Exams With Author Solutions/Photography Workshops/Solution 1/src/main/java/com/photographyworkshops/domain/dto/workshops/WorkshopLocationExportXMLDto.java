package com.photographyworkshops.domain.dto.workshops;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopLocationExportXMLDto {

    @XmlAttribute
    private String name;

    @XmlElementWrapper(name = "workshops")
    @XmlElement(name = "workshop")
    private List<WorkshopExportXMLDto> workshops;

    public WorkshopLocationExportXMLDto() {
        this.setWorkshops(new ArrayList<>());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WorkshopExportXMLDto> getWorkshops() {
        return this.workshops;
    }

    public void setWorkshops(List<WorkshopExportXMLDto> workshops) {
        this.workshops = workshops;
    }
}
