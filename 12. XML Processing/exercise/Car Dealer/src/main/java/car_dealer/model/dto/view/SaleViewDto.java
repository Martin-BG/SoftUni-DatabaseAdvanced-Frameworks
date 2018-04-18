package car_dealer.model.dto.view;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaleViewDto implements Serializable {

    private Double discount;

    private CarViewDto car;
}
