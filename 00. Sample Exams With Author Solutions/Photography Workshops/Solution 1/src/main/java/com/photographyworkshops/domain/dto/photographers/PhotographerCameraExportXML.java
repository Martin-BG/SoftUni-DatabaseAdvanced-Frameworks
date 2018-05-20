package com.photographyworkshops.domain.dto.photographers;

import com.photographyworkshops.domain.dto.lens.LenExportXML;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "photographer")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographerCameraExportXML implements Serializable {

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "primary-camera")
    private String camera;

    @XmlElementWrapper(name = "lenses")
    @XmlElement(name = "lens")
    private List<LenExportXML> lenses;

    public PhotographerCameraExportXML() {
        this.setLenses(new ArrayList<>());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCamera() {
        return this.camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public List<LenExportXML> getLenses() {
        return this.lenses;
    }

    public void setLenses(List<LenExportXML> lenses) {
        this.lenses = lenses;
    }
}
