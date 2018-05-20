package com.photographyworkshops.domain.dto.lens;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "lens")
@XmlAccessorType(XmlAccessType.FIELD)
public class LenExportXML {

    @XmlAttribute
    private String focalLength;

    @XmlAttribute
    private String maxAperture;

    public String getFocalLength() {
        return this.focalLength;
    }

    public void setFocalLength(String focalLength) {
        this.focalLength = focalLength + "mm";
    }

    public String getMaxAperture() {
        return this.maxAperture;
    }

    public void setMaxAperture(String maxAperture) {
        this.maxAperture = "f" + maxAperture;
    }
}
