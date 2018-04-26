package org.softuni.mostwanted.model.dto.binding.json;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RacerImportJsonDto implements Serializable {

    @NotNull
    @Length(min = 1, max = 255)
    private String name;

    @Min(0)
    private Integer age;

    @DecimalMin(value = "0")
    private BigDecimal bounty;

    @NotNull
    @Length(min = 1, max = 255)
    private String homeTown;
}
