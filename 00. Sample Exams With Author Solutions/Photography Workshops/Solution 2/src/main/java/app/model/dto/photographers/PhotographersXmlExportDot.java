package app.model.dto.photographers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "photographers")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographersXmlExportDot {

    @XmlElement(name = "photographer")
    List<PhotographerXmlExpotDto> photographerXmlExpotDtos;

    public List<PhotographerXmlExpotDto> getPhotographerXmlExpotDtos() {
        return photographerXmlExpotDtos;
    }

    public void setPhotographerXmlExpotDtos(List<PhotographerXmlExpotDto> photographerXmlExpotDtos) {
        this.photographerXmlExpotDtos = photographerXmlExpotDtos;
    }
}
