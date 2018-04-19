package car_dealer.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerPurchasesViewDto implements Serializable {

    @SerializedName("fullName")
    @XmlAttribute(name = "full-name")
    private String name;

    @XmlAttribute(name = "bought-cars")
    private Integer boughtCars;

    @XmlAttribute(name = "spent-money")
    private BigDecimal spentMoney;
}
