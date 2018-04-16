package product_shop.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryNameProductsCountAverageAndTotalPricesDto implements Serializable {

    @NotNull
    @Length(min = 3, max = 15)
    @SerializedName("category")
    private String name;

    private Integer productsCount;

    private Double averagePrice;

    private BigDecimal totalRevenue;
}
