package car_dealer.model.dto.binding.xml;

import car_dealer.model.dto.binding.CustomerDto;
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
public class CustomersCustomerDto implements Serializable {

    @XmlElement(name = "customer")
    private List<CustomerDto> customers;

    public CustomersCustomerDto() {
        this.customers = new ArrayList<>();
    }
}
