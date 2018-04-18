package car_dealer.model.dto.view.xml;

import car_dealer.model.dto.view.CustomerByBirthdayDto;
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
@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersCustomerByBirthdayDto implements Serializable {

    @XmlElement(name = "customer")
    private List<CustomerByBirthdayDto> customers;

    public CustomersCustomerByBirthdayDto() {
        this.customers = new ArrayList<>();
    }
}
