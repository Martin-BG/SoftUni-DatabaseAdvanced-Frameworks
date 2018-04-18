package car_dealer.model.dto.binding;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto implements Serializable {

    private String name;

    private Boolean isImporter;
}
