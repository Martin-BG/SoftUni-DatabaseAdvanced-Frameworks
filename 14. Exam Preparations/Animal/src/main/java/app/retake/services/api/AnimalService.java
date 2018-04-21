package app.retake.services.api;

import app.retake.domain.dto.AnimalsJSONExportDTO;

import java.util.List;

public interface AnimalService extends Creatable {

    List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber);
}
