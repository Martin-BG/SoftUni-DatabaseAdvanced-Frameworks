package product_shop.model.dto.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class SoldProductsDto implements Serializable {

    private Integer count;

    private Set<ProductNameAndPriceDto> soldProducts;

    public SoldProductsDto() {
        this.soldProducts = new HashSet<>();
    }
}
