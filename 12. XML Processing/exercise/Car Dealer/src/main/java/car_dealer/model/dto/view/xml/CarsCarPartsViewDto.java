package car_dealer.model.dto.view.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsCarPartsViewDto implements Serializable {

    @XmlElement(name = "car")
    private List<CarPartsViewXmlDto> cars;

    public CarsCarPartsViewDto() {
        this.cars = new ArrayList<>();
    }
}
