package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.services.api.AnimalService;

import java.util.List;

public class AnimalServiceImpl implements AnimalService {
    @Override
    public void create(AnimalJSONImportDTO dto) {

    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        return null;
    }
}
