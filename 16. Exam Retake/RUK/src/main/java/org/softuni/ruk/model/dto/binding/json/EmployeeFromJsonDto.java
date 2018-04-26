package org.softuni.ruk.model.dto.binding.json;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFromJsonDto implements Serializable {

    @NotNull
    @Length(min = 1)
    @SerializedName("full_name")
    private String fullName;

    @DecimalMin("0")
    private BigDecimal salary;

    @SerializedName("started_on")
    private Date startedOn;

    @NotNull
    @Length(min = 1)
    @SerializedName("branch_name")
    private String branchName;
}
