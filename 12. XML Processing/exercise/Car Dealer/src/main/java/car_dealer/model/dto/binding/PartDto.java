package car_dealer.model.dto.binding;

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
public class PartDto implements Serializable {

    private String name;

    private BigDecimal price;

    private Integer quantity;
}
