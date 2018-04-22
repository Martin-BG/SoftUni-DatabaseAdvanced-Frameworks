package app.retake.services.api;

import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;

import java.util.List;

public interface AnimalService extends Creatable {

    List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber);

    Animal findByPassportNumber(final String passportNumber);
}
