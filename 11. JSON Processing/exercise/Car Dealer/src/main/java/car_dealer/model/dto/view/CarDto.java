package car_dealer.model.dto.view;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarDto implements Serializable {

    private String make;

    private String model;

    private Long travelledDistance;
}
