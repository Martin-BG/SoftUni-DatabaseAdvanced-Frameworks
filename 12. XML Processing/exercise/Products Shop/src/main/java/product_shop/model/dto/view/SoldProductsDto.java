package product_shop.model.dto.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@XmlRootElement(name = "sold_products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsDto implements Serializable {

    @XmlAttribute
    private Integer count;

    private Set<ProductNameAndPriceDto> soldProducts;

    public SoldProductsDto() {
        this.soldProducts = new HashSet<>();
    }
}
