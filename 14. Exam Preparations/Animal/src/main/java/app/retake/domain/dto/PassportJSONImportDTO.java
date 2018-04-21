package app.retake.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PassportJSONImportDTO implements Serializable {

    @NotNull
    @Pattern(regexp = "^\\S{7}\\d{3}$")
    private String serialNumber;

    @NotNull
    @Pattern(regexp = "^(?:\\+359|0)\\d{9}$")
    private String ownerPhoneNumber;

    @Length(min = 3, max = 30)
    private String ownerName;

    private Date registrationDate;
}
