package org.softuni.mostwanted.persistance.service.impl;

import org.softuni.mostwanted.config.Config;
import org.softuni.mostwanted.model.dto.binding.json.DistrictImportJsonDto;
import org.softuni.mostwanted.model.entity.District;
import org.softuni.mostwanted.model.entity.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.persistance.repository.DistrictRepository;
import org.softuni.mostwanted.persistance.service.api.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final ModelParser mapper;
    private final ValidationUtil validator;
    private final TownServiceImpl townService;

    @Autowired
    public DistrictServiceImpl(final DistrictRepository districtRepository,
                               final ModelParser mapper,
                               final ValidationUtil validator,
                               final TownServiceImpl townService) {
        this.districtRepository = districtRepository;
        this.mapper = mapper;
        this.validator = validator;
        this.townService = townService;
    }

    @Override
    public <T> String create(final T t) {
        DistrictImportJsonDto dto = (DistrictImportJsonDto) t;
        String townName = dto.getTownName();
        Town town = this.townService.findOneByName(townName);

        if (town == null || !this.validator.isValid(dto)) {
            return Config.ERROR_INCORRECT_DATA;
        }

        String districtName = dto.getName();

        District existing = this.districtRepository.findOneByName(districtName);
        if (existing != null) {
            return Config.ERROR_DUPLICATE_DATA;
        }

        District district = this.mapper.convert(dto, District.class);
        district.setTown(town);
        this.districtRepository.saveAndFlush(district);

        return String.format(Config.SUCCESSFULLY_IMPORTED, "District", district.getName());
    }

    District findOneByName(final String name) {
        return this.districtRepository.findOneByName(name);
    }
}
