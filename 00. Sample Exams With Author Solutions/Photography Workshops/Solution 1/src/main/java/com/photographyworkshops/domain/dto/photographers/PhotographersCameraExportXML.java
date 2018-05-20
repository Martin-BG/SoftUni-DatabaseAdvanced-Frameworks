package com.photographyworkshops.domain.dto.photographers;

import com.photographyworkshops.domain.dto.lens.LenExportXML;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "photographers")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographersCameraExportXML implements Serializable {

    @XmlElement(name = "photograph")
    private List<PhotographerCameraExportXML> photographers;

    public PhotographersCameraExportXML() {
        this.setPhotographers(new ArrayList<>());
    }

    public List<PhotographerCameraExportXML> getPhotographers() {
        return this.photographers;
    }

    public void setPhotographers(List<PhotographerCameraExportXML> photographers) {
        this.photographers = photographers;
    }
}
