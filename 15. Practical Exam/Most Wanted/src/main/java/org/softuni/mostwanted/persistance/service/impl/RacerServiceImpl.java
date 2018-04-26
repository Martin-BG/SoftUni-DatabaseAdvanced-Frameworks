package org.softuni.mostwanted.persistance.service.impl;

import org.softuni.mostwanted.config.Config;
import org.softuni.mostwanted.model.dto.binding.json.RacerImportJsonDto;
import org.softuni.mostwanted.model.dto.view.json.RacersWithCarsExportDto;
import org.softuni.mostwanted.model.entity.Racer;
import org.softuni.mostwanted.model.entity.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.persistance.repository.RacerRepository;
import org.softuni.mostwanted.persistance.service.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacerServiceImpl implements RacerService {

    private final RacerRepository racerRepository;
    private final ModelParser mapper;
    private final ValidationUtil validator;
    private final TownServiceImpl townService;

    @Autowired
    public RacerServiceImpl(final RacerRepository racerRepository,
                            final ModelParser mapper,
                            final ValidationUtil validator,
                            final TownServiceImpl townService) {
        this.racerRepository = racerRepository;
        this.mapper = mapper;
        this.validator = validator;
        this.townService = townService;
    }

    Racer findOneByName(final String name) {
        return this.racerRepository.findOneByName(name);
    }

    @Override
    public <T> String create(final T t) {
        RacerImportJsonDto dto = (RacerImportJsonDto) t;

        if (this.racerRepository.findOneByName(dto.getName()) != null) {
            return Config.ERROR_DUPLICATE_DATA;
        }

        Town town = this.townService.findOneByName(dto.getHomeTown());

        if (!this.validator.isValid(dto) || town == null) {
            return Config.ERROR_INCORRECT_DATA;
        }

        Racer racer = this.mapper.convert(dto, Racer.class);
        racer.setHomeTown(town);
        this.racerRepository.saveAndFlush(racer);

        return String.format(Config.SUCCESSFULLY_IMPORTED, "Racer", racer.getName());
    }

    @Override
    public List<RacersWithCarsExportDto> getAllRacersWithCars() {
        return this.racerRepository
                .getAllRacersWithCars()
                .stream()
                .map(racer -> {
                    final RacersWithCarsExportDto dto = new RacersWithCarsExportDto();
                    dto.setName(racer.getName());
                    dto.setAge(racer.getAge());
                    dto.setCars(racer
                            .getCars()
                            .stream()
                            .map(car -> String.format(
                                    "%s %s %d",
                                    car.getBrand(), car.getModel(), car.getYearOfProduction()))
                            .collect(Collectors.toList())
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
