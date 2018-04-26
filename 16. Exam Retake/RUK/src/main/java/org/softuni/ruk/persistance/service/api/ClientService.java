package org.softuni.ruk.persistance.service.api;

import org.softuni.ruk.model.dto.view.xml.FamilyGuyWithCardsWrapperDto;

public interface ClientService extends Creatable {

    FamilyGuyWithCardsWrapperDto findClientWithMostCards();
}
