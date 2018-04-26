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
public class CarImportJsonDto implements Serializable {

    @NotNull
    @Length(min = 1, max = 255)
    private String brand;

    @NotNull
    @Length(min = 1, max = 255)
    private String model;

    @DecimalMin(value = "0")
    private BigDecimal price;

    @NotNull
    @Min(0)
    private Integer yearOfProduction;

    private Double maxSpeed;

    private Double zeroToSixty;

    @NotNull
    @Length(min = 1, max = 255)
    private String racerName;
}
