package org.softuni.ruk.model.dto.binding.json;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientFromJsonDto implements Serializable {

    @NotNull
    @Length(min = 1)
    @SerializedName("first_name")
    private String firstName;

    @NotNull
    @Length(min = 1)
    @SerializedName("last_name")
    private String lastName;

    @Min(0)
    private Integer age;

    @NotNull
    @Length(min = 1)
    @SerializedName("appointed_employee")
    private String appointedEmployee;
}
