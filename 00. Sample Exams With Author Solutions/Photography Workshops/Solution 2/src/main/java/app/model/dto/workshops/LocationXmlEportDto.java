package app.model.dto.workshops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class LocationXmlEportDto {

    @XmlAttribute
    private String name;

    @XmlElement(name = "workshop")
    private List<WorkshopExportXmlDto> workshopExportXmlDtos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WorkshopExportXmlDto> getWorkshopExportXmlDtos() {
        return workshopExportXmlDtos;
    }

    public void setWorkshopExportXmlDtos(List<WorkshopExportXmlDto> workshopExportXmlDtos) {
        this.workshopExportXmlDtos = workshopExportXmlDtos;
    }
}
