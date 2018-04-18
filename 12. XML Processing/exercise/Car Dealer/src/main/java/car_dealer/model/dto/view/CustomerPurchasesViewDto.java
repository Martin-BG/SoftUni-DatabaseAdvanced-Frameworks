package car_dealer.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPurchasesViewDto implements Serializable {

    @SerializedName("fullName")
    private String name;

    private Integer boughtCars;

    private BigDecimal spentMoney;
}
