package app.retake.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AnimalsJSONExportDTO implements Serializable {

    private String ownerName;
    private String animalName;
    private Integer age;
    private String serialNumber;
    private Date registeredOn;

    public AnimalsJSONExportDTO(final String ownerName,
                                final String animalName,
                                final Integer age,
                                final String serialNumber,
                                final Date registeredOn) {
        this.ownerName = ownerName;
        this.animalName = animalName;
        this.age = age;
        this.serialNumber = serialNumber;
        this.registeredOn = registeredOn;
    }
}
