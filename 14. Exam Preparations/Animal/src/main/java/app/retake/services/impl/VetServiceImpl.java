package app.retake.services.impl;

import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.repositories.VetRepository;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    @Autowired
    public VetServiceImpl(final VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public void create(VetXMLImportDTO dto) {
    }
}
