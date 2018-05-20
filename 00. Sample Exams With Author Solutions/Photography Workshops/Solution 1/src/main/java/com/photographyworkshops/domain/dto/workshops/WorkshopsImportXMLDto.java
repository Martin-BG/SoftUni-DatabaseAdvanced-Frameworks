package com.photographyworkshops.domain.dto.workshops;

import com.photographyworkshops.domain.dto.photographers.PhotographerImportXMLDto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "workshops")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopsImportXMLDto {

    @XmlElement(name = "workshop")
    private List<WorkshopImportXMLDto> workshops;

    public WorkshopsImportXMLDto() {
       this.setWorkshops(new ArrayList<>());
    }

    public List<WorkshopImportXMLDto> getWorkshops() {
        return this.workshops;
    }

    public void setWorkshops(List<WorkshopImportXMLDto> workshops) {
        this.workshops = workshops;
    }
}
