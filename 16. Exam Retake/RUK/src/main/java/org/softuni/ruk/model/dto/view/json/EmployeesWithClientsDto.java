package org.softuni.ruk.model.dto.view.json;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmployeesWithClientsDto implements Serializable {

    List<String> clients = new ArrayList<>();

    @SerializedName("full_name")
    private String fullName;

    @DecimalMin("0")
    private BigDecimal salary;

    @SerializedName("started_on")
    private Date startedOn;
}
