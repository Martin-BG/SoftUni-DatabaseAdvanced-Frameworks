package car_dealer.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CarViewDto implements Serializable {

    @SerializedName("Id")
    private Long id;

    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName("TravelledDistance")
    private Long travelledDistance;
}
