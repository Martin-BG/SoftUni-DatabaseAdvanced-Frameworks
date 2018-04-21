package app.retake.services.impl;

import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.services.api.VetService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VetServiceImpl implements VetService {

    @Override
    public void create(VetXMLImportDTO dto) {
    }
}
