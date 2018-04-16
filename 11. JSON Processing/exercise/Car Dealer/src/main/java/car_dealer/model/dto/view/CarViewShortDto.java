package car_dealer.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarViewShortDto implements Serializable {

    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName("TravelledDistance")
    private Long travelledDistance;
}
