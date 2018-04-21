package app.retake.services.impl;


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

    private final AnimalAidRepository animalAidRepository;
    private final ModelParser modelParser;

    @Autowired
    public AnimalAidServiceImpl(final AnimalAidRepository animalAidRepository,
                                final ModelParser modelParser) {
        this.animalAidRepository = animalAidRepository;
        this.modelParser = modelParser;
    }

    @Override
    public <T> void create(T dto) {
        AnimalAid animalAid = this.modelParser.convert(dto, AnimalAid.class);
        final AnimalAid existing = this.findByName(animalAid.getName());
        if (existing != null) {
            if (!animalAid.getPrice().equals(existing.getPrice())) {
                existing.setPrice(animalAid.getPrice());
            }
        } else {
            this.animalAidRepository.save(animalAid);
        }
    }

    @Override
    public AnimalAid findByName(final String name) {
        return this.animalAidRepository.findByName(name);
    }
}
