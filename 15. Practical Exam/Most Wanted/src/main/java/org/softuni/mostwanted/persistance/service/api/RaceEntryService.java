package org.softuni.mostwanted.persistance.service.api;

import org.softuni.mostwanted.model.dto.view.xml.MostWantedExportWrapperDto;

public interface RaceEntryService extends Creatable {

    MostWantedExportWrapperDto getMostWantedRacer();
}
