package car_dealer.model.dto.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CarDto implements Serializable {

    private String make;

    private String model;

    private Long travelledDistance;
}
