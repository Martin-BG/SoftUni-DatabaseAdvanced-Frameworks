package car_dealer.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SaleDetailsViewDto implements Serializable {

    private CarViewShortDto car;

    private String customerName;

    @SerializedName("Discount")
    private Double discount;

    private BigDecimal price;

    private BigDecimal priceWithDiscount;
}
