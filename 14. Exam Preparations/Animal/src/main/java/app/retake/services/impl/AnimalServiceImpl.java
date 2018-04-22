package app.retake.services.impl;

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
    private final PassportRepository passportRepository;
    private final ModelParser modelParser;

    @Autowired
    public AnimalServiceImpl(final AnimalRepository animalRepository,
                             final PassportRepository passportRepository,
                             final ModelParser modelParser) {
        this.animalRepository = animalRepository;
        this.passportRepository = passportRepository;
        this.modelParser = modelParser;
    }

    @Override
    public <T> void create(final T dto) {
        final Animal animal = this.modelParser.convert(dto, Animal.class);
        if (passportRepository.findOne(animal.getPassport().getSerialNumber()) == null) {
            this.animalRepository.saveAndFlush(animal);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public Animal findByPassportNumber(final String passportNumber) {
        return this.animalRepository.findByPassport_SerialNumber(passportNumber);
    }


}
