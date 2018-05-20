package app.model.dto.workshops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "locations")
public class LocationsWrapperXmlDto {

    @XmlElement(name = "location")
    private List<LocationXmlEportDto> locationXmlEportDtos = new ArrayList<>();

    public List<LocationXmlEportDto> getLocationXmlEportDtos() {
        return locationXmlEportDtos;
    }

    public void setLocationXmlEportDtos(List<LocationXmlEportDto> locationXmlEportDtos) {
        this.locationXmlEportDtos = locationXmlEportDtos;
    }

    public void addLocationXmlEpxortDto(LocationXmlEportDto locationXmlEportDto) {
        locationXmlEportDtos.add(locationXmlEportDto);
    }


}
