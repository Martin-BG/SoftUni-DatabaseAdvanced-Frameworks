package car_dealer.model.dto.binding.xml;

import car_dealer.model.dto.binding.SupplierDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersSupplierDto implements Serializable {

    @XmlElement(name = "supplier")
    private Set<SupplierDto> suppliers;

    public SuppliersSupplierDto() {
        this.suppliers = new HashSet<>();
    }
}
