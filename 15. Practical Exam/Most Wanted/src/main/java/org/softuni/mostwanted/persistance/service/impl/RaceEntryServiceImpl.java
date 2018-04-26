package org.softuni.mostwanted.persistance.service.impl;

import org.softuni.mostwanted.config.Config;
import org.softuni.mostwanted.model.dto.binding.xml.RaceEntryImportXmlDto;
import org.softuni.mostwanted.model.dto.view.xml.MostWantedEntryExportDto;
import org.softuni.mostwanted.model.dto.view.xml.MostWantedExportDto;
import org.softuni.mostwanted.model.dto.view.xml.MostWantedExportWrapperDto;
import org.softuni.mostwanted.model.entity.Car;
import org.softuni.mostwanted.model.entity.RaceEntry;
import org.softuni.mostwanted.model.entity.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.persistance.repository.RaceEntryRepository;
import org.softuni.mostwanted.persistance.service.api.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RaceEntryServiceImpl implements RaceEntryService {

    private final RaceEntryRepository raceEntryRepository;
    private final ModelParser mapper;
    private final CarServiceImpl carService;
    private final RacerServiceImpl racerService;

    @Autowired
    public RaceEntryServiceImpl(final RaceEntryRepository raceEntryRepository,
                                final ModelParser modelMapper,
                                final CarServiceImpl carService,
                                final RacerServiceImpl racerService) {
        this.raceEntryRepository = raceEntryRepository;
        this.mapper = modelMapper;
        this.carService = carService;
        this.racerService = racerService;
    }

    @Override
    public <T> String create(final T t) {
        RaceEntryImportXmlDto dto = (RaceEntryImportXmlDto) t;

        Car car = this.carService.findOneById(dto.getCar());
        Racer racer = this.racerService.findOneByName(dto.getRacerName());

        if (racer == null || car == null) {
            return Config.ERROR_INCORRECT_DATA;
        }

        RaceEntry raceEntry = this.mapper.convert(dto, RaceEntry.class);

        raceEntry.setCar(car);
        raceEntry.setRacer(racer);
        this.raceEntryRepository.saveAndFlush(raceEntry);

        return String.format(Config.SUCCESSFULLY_IMPORTED, "RaceEntry", raceEntry.getId());
    }

    RaceEntry findOneById(final Long id) {
        return this.raceEntryRepository.findOne(id);
    }

    @Override
    public MostWantedExportWrapperDto getMostWantedRacer() {
        final List<RaceEntry> raceEntries = this.raceEntryRepository.getEntriesOfMostWantedRacer();

        return new MostWantedExportWrapperDto(new MostWantedExportDto(
                raceEntries.get(0).getRacer().getName(),
                raceEntries.stream()
                        .map(entry -> new MostWantedEntryExportDto(
                                entry.getFinishTime(),
                                String.format("%s %s @ %d",
                                        entry.getCar().getBrand(),
                                        entry.getCar().getModel(),
                                        entry.getCar().getYearOfProduction())))
                        .collect(Collectors.toList())));
    }
}
