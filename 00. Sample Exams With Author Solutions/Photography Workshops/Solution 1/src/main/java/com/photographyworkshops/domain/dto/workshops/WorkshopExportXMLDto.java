package com.photographyworkshops.domain.dto.workshops;

import com.photographyworkshops.domain.dto.photographers.PhotographersLocationExportXMLDto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "workshop")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopExportXMLDto {

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "total-profit")
    private double totalProfit;

    @XmlElement(name = "participants")
    private PhotographersLocationExportXMLDto photographers;

    public WorkshopExportXMLDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalProfit() {
        return this.totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public PhotographersLocationExportXMLDto getPhotographers() {
        return this.photographers;
    }

    public void setPhotographers(PhotographersLocationExportXMLDto photographers) {
        this.photographers = photographers;
    }
}
