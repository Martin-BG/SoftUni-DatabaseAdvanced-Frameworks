package com.photographyworkshops.domain.dto.workshops;

import com.photographyworkshops.domain.dto.photographers.PhotographerImportXMLDto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "workshop")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopImportXMLDto {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "start-date")
    private Date startDate;

    @XmlAttribute(name = "end-date")
    private Date endDate;

    @XmlAttribute(name = "location")
    private String location;

    @XmlAttribute(name = "pricePerParticipant")
    private BigDecimal pricePerParticipant;

    @XmlElement(name = "trainer")
    private String trainer;

    @XmlElementWrapper(name = "participants")
    @XmlElement(name = "participant")
    private List<PhotographerImportXMLDto> photographers;

    public WorkshopImportXMLDto() {
        this.setPhotographers(new ArrayList<>());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPricePerParticipant() {
        return this.pricePerParticipant;
    }

    public void setPricePerParticipant(BigDecimal pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    public String getTrainer() {
        return this.trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public List<PhotographerImportXMLDto> getPhotographers() {
        return this.photographers;
    }

    public void setPhotographers(List<PhotographerImportXMLDto> photographers) {
        this.photographers = photographers;
    }
}
