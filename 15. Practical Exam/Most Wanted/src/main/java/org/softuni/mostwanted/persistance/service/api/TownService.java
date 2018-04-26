package org.softuni.mostwanted.persistance.service.api;

import org.softuni.mostwanted.model.dto.view.json.TownsByRacersExportDto;

import java.util.List;

public interface TownService extends Creatable {

    List<TownsByRacersExportDto> findTownsByRacers();
}
