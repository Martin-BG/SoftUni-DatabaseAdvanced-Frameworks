package org.softuni.ruk.model.dto.view.json;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @SerializedName("full_name")
    private String fullName;

    private BigDecimal salary;

    @SerializedName("started_on")
    private Date startedOn;

    private List<String> clients = new ArrayList<>();
}
