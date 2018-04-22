package app.retake.domain.dto;

import app.retake.Config;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@XmlRootElement(name = "vet")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetXMLImportDTO implements Serializable, Importable {

    @NotNull
    @Length(min = 3, max = 40)
    private String name;

    @NotNull
    @Length(min = 3, max = 50)
    private String profession;

    @NotNull
    @Range(min = 22, max = 65)
    private Integer age;

    @NotNull
    @Pattern(regexp = "^(?:\\+359|0)\\d{9}$")
    @XmlElement(name = "phone-number")
    private String phoneNumber;

    @Override
    public String errorMessage() {
        return Config.MESSAGE_ERROR_INVALID_DATA;
    }

    @Override
    public String successMessage() {
        return String.format(Config.MESSAGE_RECORD_S_SUCCESSFULLY_IMPORTED, this.name);
    }
}
