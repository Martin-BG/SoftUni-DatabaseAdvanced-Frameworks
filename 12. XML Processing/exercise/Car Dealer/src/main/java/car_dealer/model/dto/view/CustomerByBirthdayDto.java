package car_dealer.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerByBirthdayDto implements Serializable {

    @SerializedName("Id")
    @XmlElement
    private Long id;

    @SerializedName("Name")
    @XmlElement
    private String name;

    @SerializedName("BirthDate")
    @XmlElement(name = "birth-date")
    private Date birthDate;

    @SerializedName("IsYoungDriver")
    @XmlElement(name = "is-youn-driver")
    private boolean isYoungDriver;

    @SerializedName("Sales")
    @XmlTransient
    private Set<SaleViewDto> purchases;

    public CustomerByBirthdayDto() {
        this.purchases = new HashSet<>();
    }
}
