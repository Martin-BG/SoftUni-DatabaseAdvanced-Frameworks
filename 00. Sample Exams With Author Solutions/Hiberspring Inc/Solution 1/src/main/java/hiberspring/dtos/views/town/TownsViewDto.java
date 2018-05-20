package hiberspring.dtos.views.town;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownsViewDto {

    @XmlElement(name = "town")
    private List<TownViewDto> townViewDtos;

    public TownsViewDto() {
    }

    public List<TownViewDto> getTownViewDtos() {
        return this.townViewDtos;
    }

    public void setTownViewDtos(List<TownViewDto> townViewDtos) {
        this.townViewDtos = townViewDtos;
    }
}
