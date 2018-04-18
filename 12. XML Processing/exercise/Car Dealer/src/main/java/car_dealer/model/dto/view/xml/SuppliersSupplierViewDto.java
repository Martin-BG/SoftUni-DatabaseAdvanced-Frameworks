package car_dealer.model.dto.view.xml;

import car_dealer.model.dto.view.SupplierViewDto;
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
@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersSupplierViewDto implements Serializable {

    @XmlElement(name = "supplier")
    private List<SupplierViewDto> suppliers;

    public SuppliersSupplierViewDto() {
        this.suppliers = new ArrayList<>();
    }
}
