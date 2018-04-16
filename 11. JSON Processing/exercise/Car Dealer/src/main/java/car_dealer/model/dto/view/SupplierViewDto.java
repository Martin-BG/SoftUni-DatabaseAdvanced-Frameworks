package car_dealer.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SupplierViewDto implements Serializable {

    @SerializedName("Id")
    private Long id;

    @SerializedName("Name")
    private String name;

    private Long partsCount;
}
