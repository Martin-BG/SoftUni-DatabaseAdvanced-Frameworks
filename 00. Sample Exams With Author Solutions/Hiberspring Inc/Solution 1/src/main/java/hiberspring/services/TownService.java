package hiberspring.services;

import hiberspring.dtos.imports.TownImportFromJsonDto;
import hiberspring.dtos.views.town.TownsViewDto;
import hiberspring.entities.Town;

public interface TownService {

    Town createOne(TownImportFromJsonDto townImportFromJsonDto);

    TownsViewDto getTowns();
}
