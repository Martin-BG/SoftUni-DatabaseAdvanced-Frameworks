package org.softuni.mostwanted.persistance.service.api;

import org.softuni.mostwanted.model.dto.view.json.RacersWithCarsExportDto;

import java.util.List;

public interface RacerService extends Creatable {

    List<RacersWithCarsExportDto> getAllRacersWithCars();
}
