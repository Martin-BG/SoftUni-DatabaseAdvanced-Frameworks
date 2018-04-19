package car_dealer.model.dto.view.xml;

import car_dealer.model.dto.view.SaleDetailsViewDto;
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
@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesSaleDetailsViewDto implements Serializable {

    @XmlElement(name = "sale")
    private List<SaleDetailsViewDto> sales;

    public SalesSaleDetailsViewDto() {
        this.sales = new ArrayList<>();
    }
}
