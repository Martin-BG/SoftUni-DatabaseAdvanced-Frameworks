package car_dealer.model.dto.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerDto implements Serializable {

    private String name;

    private Date birthDate;

    private Boolean isYoungDriver;
}
