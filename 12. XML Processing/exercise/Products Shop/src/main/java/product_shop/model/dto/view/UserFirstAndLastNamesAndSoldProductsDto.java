package product_shop.model.dto.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserFirstAndLastNamesAndSoldProductsDto implements Serializable {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @NotNull
    @Length(min = 3)
    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private Set<ProductNamePriceBuyerFirstAndLastNamesDto> soldProducts;

    public UserFirstAndLastNamesAndSoldProductsDto() {
        this.soldProducts = new HashSet<>();
    }
}
