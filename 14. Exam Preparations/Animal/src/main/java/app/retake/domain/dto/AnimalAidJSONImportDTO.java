package app.retake.domain.dto;

import app.retake.Config;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AnimalAidJSONImportDTO implements Serializable, Importable {

    @Length(min = 3, max = 50)
    @NotNull
    private String name;

    @DecimalMin(value = "0.01")
    @NotNull
    private BigDecimal price;

    @Override
    public String errorMessage() {
        return Config.MESSAGE_ERROR_INVALID_DATA;
    }

    @Override
    public String successMessage() {
        return String.format(Config.MESSAGE_RECORD_S_SUCCESSFULLY_IMPORTED, this.getName());
    }
}
