package product_shop.model.dto.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDto implements Serializable {

    @NotNull
    @Length(min = 3)
    private String name;

    @NotNull
    private BigDecimal price;
}
