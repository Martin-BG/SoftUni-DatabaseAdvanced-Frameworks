package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final ModelParser mapper;
    private final PassportRepository passportRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository,
                             ModelParser mapper,
                             PassportRepository passportRepository) {
        this.animalRepository = animalRepository;
        this.mapper = mapper;
        this.passportRepository = passportRepository;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) {
        Animal animal = this.mapper.convert(dto, Animal.class);
        if (this.passportRepository.findOne(animal.getPassport().getSerialNumber()) != null) {
            throw new IllegalArgumentException();
        }

        this.animalRepository.saveAndFlush(animal);
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        return this.animalRepository.allAnimalsByOwnerNumber(phoneNumber);
    }

    @Override
    public Animal getByPassportSerialNumber(String name) {
        return this.animalRepository.findOneByPassportSerialNumber(name);
    }
}
