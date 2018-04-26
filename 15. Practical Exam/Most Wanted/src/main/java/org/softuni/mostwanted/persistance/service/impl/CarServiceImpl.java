package org.softuni.mostwanted.persistance.service.impl;

import org.softuni.mostwanted.config.Config;
import org.softuni.mostwanted.model.dto.binding.json.CarImportJsonDto;
import org.softuni.mostwanted.model.entity.Car;
import org.softuni.mostwanted.model.entity.Racer;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.persistance.repository.CarRepository;
import org.softuni.mostwanted.persistance.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelParser mapper;
    private final ValidationUtil validator;
    private final RacerServiceImpl racerService;

    @Autowired
    public CarServiceImpl(final CarRepository carRepository,
                          final ModelParser mapper,
                          final ValidationUtil validator,
                          final RacerServiceImpl racerService) {
        this.carRepository = carRepository;
        this.mapper = mapper;
        this.validator = validator;
        this.racerService = racerService;
    }

    @Override
    public <T> String create(final T t) {
        CarImportJsonDto dto = (CarImportJsonDto) t;
        String racerName = dto.getRacerName();
        Racer racer = racerService.findOneByName(racerName);

        if (!this.validator.isValid(dto) || racer == null) {
            return Config.ERROR_INCORRECT_DATA;
        }

        Car car = this.mapper.convert(dto, Car.class);
        car.setRacer(racer);
        this.carRepository.saveAndFlush(car);
        racer.getCars().add(car);

        return String.format(Config.SUCCESSFULLY_IMPORTED, "Car",
                String.format("%s %s @ %d", car.getBrand(), car.getModel(), car.getYearOfProduction()));
    }

    Car findOneById(final Long id) {
        return this.carRepository.findOne(id);
    }
}
