package product_shop.model.dto.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto implements Serializable {

    private String firstName;

    @NotNull
    @Length(min = 3)
    private String lastName;

    private Integer age;

    private SoldProductsDto soldProducts;
}
