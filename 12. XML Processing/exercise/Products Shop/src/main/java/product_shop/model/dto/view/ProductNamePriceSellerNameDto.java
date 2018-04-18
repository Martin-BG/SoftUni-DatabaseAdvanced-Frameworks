package product_shop.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
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
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductNamePriceSellerNameDto implements Serializable {

    @NotNull
    @Length(min = 3)
    @XmlAttribute(name = "name")
    private String name;

    @NotNull
    @XmlAttribute(name = "price")
    private BigDecimal price;

    @NotNull
    @Length(min = 3)
    @SerializedName("seller")
    @XmlAttribute(name = "seller")
    private String sellerFullName;
}
