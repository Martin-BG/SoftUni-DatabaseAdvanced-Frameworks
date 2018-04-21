package app.retake.domain.dto;

import app.retake.Config;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AnimalJSONImportDTO implements Serializable, Importable {

    @NotNull
    @Length(min = 3, max = 20)
    private String name;

    @NotNull
    @Length(min = 3, max = 20)
    private String type;

    @Min(1)
    private Integer age;

    @Valid
    private PassportJSONImportDTO passport;

    @Override
    public String errorMessage() {
        return Config.MESSAGE_ERROR_INVALID_DATA;
    }

    @Override
    public String successMessage() {
        return String.format(Config.MESSAGE_RECORD_S_PASSPORT_S_SUCCESSFULLY_IMPORTED,
                this.name, this.passport.getSerialNumber());
    }
}
