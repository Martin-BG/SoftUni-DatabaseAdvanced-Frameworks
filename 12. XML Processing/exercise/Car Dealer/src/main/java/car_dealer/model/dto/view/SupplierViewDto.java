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
@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierViewDto implements Serializable {

    @SerializedName("Id")
    @XmlAttribute
    private Long id;

    @SerializedName("Name")
    @XmlAttribute
    private String name;

    @XmlAttribute(name = "parts-count")
    private Long partsCount;
}
