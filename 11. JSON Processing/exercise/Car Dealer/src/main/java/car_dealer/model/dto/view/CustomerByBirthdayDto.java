package car_dealer.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class CustomerByBirthdayDto implements Serializable {

    @SerializedName("Id")
    private Long id;

    @SerializedName("Name")
    private String name;

    @SerializedName("BirthDate")
    private Date birthDate;

    @SerializedName("IsYoungDriver")
    private boolean isYoungDriver;

    @SerializedName("Sales")
    private Set<SaleViewDto> purchases;

    public CustomerByBirthdayDto() {
        this.purchases = new HashSet<>();
    }
}
