package car_dealer.model.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarViewDto implements Serializable {

    @SerializedName("Id")
    @XmlAttribute
    private Long id;

    @SerializedName("Make")
    @XmlAttribute
    private String make;

    @SerializedName("Model")
    @XmlAttribute
    private String model;

    @SerializedName("TravelledDistance")
    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;
}
