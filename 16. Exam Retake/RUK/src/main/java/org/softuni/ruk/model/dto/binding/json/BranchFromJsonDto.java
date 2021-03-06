package org.softuni.ruk.model.dto.binding.json;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BranchFromJsonDto implements Serializable {

    @NotNull
    @Length(min = 1)
    private String name;
}
