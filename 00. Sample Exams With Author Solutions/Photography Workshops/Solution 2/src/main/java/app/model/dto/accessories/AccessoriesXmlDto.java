package app.model.dto.accessories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "accessories")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessoriesXmlDto {

    @XmlElement(name = "accessory")
    private AccessoryXMLDto[] accessoryXMLDtos;

    public AccessoryXMLDto[] getAccessoryXMLDtos() {
        return accessoryXMLDtos;
    }

    public void setAccessoryXMLDtos(AccessoryXMLDto[] accessoryXMLDtos) {
        this.accessoryXMLDtos = accessoryXMLDtos;
    }
}
