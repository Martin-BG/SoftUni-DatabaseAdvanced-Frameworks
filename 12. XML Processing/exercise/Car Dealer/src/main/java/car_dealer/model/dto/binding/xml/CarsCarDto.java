package car_dealer.model.dto.binding.xml;

import car_dealer.model.dto.binding.CarDto;
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
public class CarsCarDto implements Serializable {

    @XmlElement(name = "car")
    private List<CarDto> cars;

    public CarsCarDto() {
        this.cars = new ArrayList<>();
    }
}
