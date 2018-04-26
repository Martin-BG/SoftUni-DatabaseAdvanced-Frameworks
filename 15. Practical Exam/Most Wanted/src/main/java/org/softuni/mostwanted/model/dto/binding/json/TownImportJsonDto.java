package org.softuni.mostwanted.model.dto.binding.json;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TownImportJsonDto implements Serializable {

    @NotNull
    @Length(min = 1, max = 255)
    private String name;
}
