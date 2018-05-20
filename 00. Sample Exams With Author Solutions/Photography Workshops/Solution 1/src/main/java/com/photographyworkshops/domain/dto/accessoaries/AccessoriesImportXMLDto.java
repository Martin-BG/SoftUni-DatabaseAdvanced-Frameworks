package com.photographyworkshops.domain.dto.accessoaries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "accessories")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessoriesImportXMLDto {

    @XmlElement(name = "accessory")
    private List<AccessoryImportXMLDto> accessories;

    public AccessoriesImportXMLDto() {
        this.setAccessories(new ArrayList<>());
    }

    public List<AccessoryImportXMLDto> getAccessories() {
        return this.accessories;
    }

    public void setAccessories(List<AccessoryImportXMLDto> accessories) {
        this.accessories = accessories;
    }
}
