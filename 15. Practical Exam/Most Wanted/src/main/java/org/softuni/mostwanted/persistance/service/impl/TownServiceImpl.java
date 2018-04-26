package org.softuni.mostwanted.persistance.service.impl;

import org.softuni.mostwanted.config.Config;
import org.softuni.mostwanted.model.dto.binding.json.TownImportJsonDto;
import org.softuni.mostwanted.model.dto.view.json.TownsByRacersExportDto;
import org.softuni.mostwanted.model.entity.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.persistance.repository.TownRepository;
import org.softuni.mostwanted.persistance.service.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ModelParser mapper;
    private final ValidationUtil validator;

    @Autowired
    public TownServiceImpl(final TownRepository townRepository,
                           final ModelParser mapper,
                           final ValidationUtil validator) {
        this.townRepository = townRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public <T> String create(final T t) {
        TownImportJsonDto dto = (TownImportJsonDto) t;
        if (!this.validator.isValid(dto)) {
            return Config.ERROR_INCORRECT_DATA;
        }
        if (this.townRepository.findOneByName(dto.getName()) != null) {
            return Config.ERROR_DUPLICATE_DATA;
        }

        Town town = this.mapper.convert(dto, Town.class);
        this.townRepository.saveAndFlush(town);

        return String.format(Config.SUCCESSFULLY_IMPORTED, "Town", town.getName());
    }

    Town findOneByName(final String name) {
        return this.townRepository.findOneByName(name);
    }

    @Override
    public List<TownsByRacersExportDto> findTownsByRacers() {
        return this.townRepository.findTownsByRacers();
    }
}
