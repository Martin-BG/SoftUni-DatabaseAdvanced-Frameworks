package app.retake.services.api;

import app.retake.domain.dto.AnimalAidJSONImportDTO;

public interface AnimalAidService {
    void create(AnimalAidJSONImportDTO dto);
}
