package car_dealer.model.dto.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartDto implements Serializable {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    @XmlAttribute
    private Integer quantity;
}
