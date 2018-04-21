package app.retake.services.api;

import app.retake.domain.dto.VetXMLImportDTO;

public interface VetService {
    void create(VetXMLImportDTO dto);
}
