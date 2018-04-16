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
public class PartViewDto implements Serializable {

    @SerializedName("Name")
    private String name;

    @SerializedName("Price")
    private BigDecimal price;
}
