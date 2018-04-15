package product_shop.model.dto.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserFirstAndLastNamesAndSoldProductsDto implements Serializable {

    private String firstName;

    @NotNull
    @Length(min = 3)
    private String lastName;

    private Set<ProductNamePriceSellerNameDto> sold;

    public UserFirstAndLastNamesAndSoldProductsDto() {
        this.sold = new HashSet<>();
    }
}
