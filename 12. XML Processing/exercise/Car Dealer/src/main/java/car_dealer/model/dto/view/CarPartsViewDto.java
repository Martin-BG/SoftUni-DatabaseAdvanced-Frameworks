package car_dealer.model.dto.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CarPartsViewDto implements Serializable {

    private CarViewShortDto car;

    private Set<PartViewDto> parts;
}
