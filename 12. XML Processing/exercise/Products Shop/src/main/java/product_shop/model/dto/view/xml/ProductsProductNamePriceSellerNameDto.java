package product_shop.model.dto.view.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import product_shop.model.dto.view.ProductNamePriceSellerNameDto;

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
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsProductNamePriceSellerNameDto implements Serializable {

    @XmlElement(name = "product")
    private List<ProductNamePriceSellerNameDto> products;

    public ProductsProductNamePriceSellerNameDto() {
        this.products = new ArrayList<>();
    }
}
