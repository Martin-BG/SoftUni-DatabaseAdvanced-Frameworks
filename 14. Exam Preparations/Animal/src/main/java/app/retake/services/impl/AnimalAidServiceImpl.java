package app.retake.services.impl;


import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {

    private final AnimalAidRepository animalAidRepository;

    @Autowired
    public AnimalAidServiceImpl(final AnimalAidRepository animalAidRepository) {
        this.animalAidRepository = animalAidRepository;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {
    }
}
