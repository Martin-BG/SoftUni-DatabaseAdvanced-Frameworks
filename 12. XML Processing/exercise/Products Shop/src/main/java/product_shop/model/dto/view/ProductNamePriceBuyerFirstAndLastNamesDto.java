package product_shop.model.dto.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductNamePriceBuyerFirstAndLastNamesDto implements Serializable {

    @NotNull
    @Length(min = 3)
    @XmlElement
    private String name;

    @NotNull
    @XmlElement
    private BigDecimal price;

    @XmlElement(name = "buyer-first-name")
    private String buyerFirstName;

    @NotNull
    @Length(min = 3)
    @XmlElement(name = "buyer-last-name")
    private String buyerLastName;
}
