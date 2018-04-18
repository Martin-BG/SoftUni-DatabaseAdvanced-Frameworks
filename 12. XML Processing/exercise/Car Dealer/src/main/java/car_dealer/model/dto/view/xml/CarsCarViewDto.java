package car_dealer.model.dto.view.xml;

import car_dealer.model.dto.view.CarViewDto;
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
public class CarsCarViewDto implements Serializable {

    @XmlElement(name = "car")
    private List<CarViewDto> cars;

    public CarsCarViewDto() {
        this.cars = new ArrayList<>();
    }
}
