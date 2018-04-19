package car_dealer.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarViewShortDto implements Serializable {

    @SerializedName("Make")
    @XmlAttribute
    private String make;

    @SerializedName("Model")
    @XmlAttribute
    private String model;

    @SerializedName("TravelledDistance")
    @XmlAttribute
    private Long travelledDistance;
}
