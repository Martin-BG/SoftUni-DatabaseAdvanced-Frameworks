package product_shop.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryNameProductsCountAverageAndTotalPricesDto implements Serializable {

    @NotNull
    @Length(min = 3, max = 15)
    @SerializedName("category")
    @XmlAttribute
    private String name;

    @XmlElement(name = "products-count")
    private Integer productsCount;

    @XmlElement(name = "average-price")
    private Double averagePrice;

    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;
}
