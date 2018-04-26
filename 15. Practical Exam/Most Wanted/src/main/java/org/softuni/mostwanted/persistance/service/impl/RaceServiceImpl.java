package org.softuni.mostwanted.persistance.service.impl;

import org.softuni.mostwanted.config.Config;
import org.softuni.mostwanted.model.dto.binding.xml.RaceImportXmlDto;
import org.softuni.mostwanted.model.entity.District;
import org.softuni.mostwanted.model.entity.Race;
import org.softuni.mostwanted.model.entity.RaceEntry;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.persistance.repository.RaceRepository;
import org.softuni.mostwanted.persistance.service.api.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;
    private final ModelParser mapper;
    private final ValidationUtil validator;
    private final DistrictServiceImpl districtService;
    private final RaceEntryServiceImpl raceEntryService;

    @Autowired
    public RaceServiceImpl(final RaceRepository raceRepository,
                           final ModelParser mapper,
                           final ValidationUtil validator,
                           final DistrictServiceImpl districtService,
                           final RaceEntryServiceImpl raceEntryService) {
        this.raceRepository = raceRepository;
        this.mapper = mapper;
        this.validator = validator;
        this.districtService = districtService;
        this.raceEntryService = raceEntryService;
    }

    @Override
    public <T> String create(final T t) {
        RaceImportXmlDto dto = (RaceImportXmlDto) t;

        District district = this.districtService.findOneByName(dto.getDistrict());

        Set<RaceEntry> raceEntries = dto.getEntries()
                .stream()
                .map(entry -> this.raceEntryService.findOneById(entry.getId()))
                .collect(Collectors.toSet());

        if (!this.validator.isValid(dto) || district == null || raceEntries.contains(null)) {
            return Config.ERROR_INCORRECT_DATA;
        }

        Race race = this.mapper.convert(dto, Race.class);

        race.setDistrict(district);
        race.setEntries(raceEntries);

        this.raceRepository.saveAndFlush(race);

        raceEntries.forEach(raceEntry -> raceEntry.setRace(race));

        return String.format(Config.SUCCESSFULLY_IMPORTED, "Race", race.getId());
    }
}
