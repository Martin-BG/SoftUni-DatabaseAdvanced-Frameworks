package app.retake.services.impl;


import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {

    private final AnimalAidRepository repository;
    private final ModelParser modelParser;

    @Autowired
    public AnimalAidServiceImpl(AnimalAidRepository repository,
                                ModelParser modelParser) {
        this.repository = repository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {
        AnimalAid animalAid = this.repository.findByName(dto.getName());

        if (animalAid != null) {
            animalAid.setPrice(dto.getPrice());

        } else {
            animalAid = this.modelParser.convert(dto, AnimalAid.class);
        }
        this.repository.saveAndFlush(animalAid);
    }

    @Override
    public AnimalAid getOneByName(String name) {
        return this.repository.findByName(name);
    }
}
