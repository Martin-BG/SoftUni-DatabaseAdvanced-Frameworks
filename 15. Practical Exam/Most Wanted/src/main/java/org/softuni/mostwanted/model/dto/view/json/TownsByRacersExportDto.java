package org.softuni.mostwanted.model.dto.view.json;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TownsByRacersExportDto implements Serializable {

    private String name;
    private Long racers;

}
