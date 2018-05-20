package com.photographyworkshops.domain.dto.photographers;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "participants")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographersLocationExportXMLDto {

    @XmlAttribute
    private long count;

    @XmlElement(name = "participant")
    private List<String> photographers;

    public PhotographersLocationExportXMLDto() {
        this.setPhotographers(new ArrayList<>());
    }

    public List<String> getPhotographers() {
        return this.photographers;
    }

    public void setPhotographers(List<String> photographers) {
        this.photographers = photographers;
    }

    public long getCount() {
        return this.count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
