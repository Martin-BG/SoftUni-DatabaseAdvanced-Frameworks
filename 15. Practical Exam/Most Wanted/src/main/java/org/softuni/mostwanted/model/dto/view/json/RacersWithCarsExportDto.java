package org.softuni.mostwanted.model.dto.view.json;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RacersWithCarsExportDto implements Serializable {

    private String name;
    private Integer age;
    private List<String> cars;
}
