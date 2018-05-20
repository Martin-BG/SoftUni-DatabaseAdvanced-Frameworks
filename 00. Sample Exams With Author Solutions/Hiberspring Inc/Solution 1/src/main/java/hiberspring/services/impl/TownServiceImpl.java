package hiberspring.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiberspring.dtos.imports.TownImportFromJsonDto;
import hiberspring.dtos.views.town.TownViewDto;
import hiberspring.dtos.views.town.TownsViewDto;
import hiberspring.entities.Town;
import hiberspring.repositories.TownRepository;
import hiberspring.services.TownService;
import hiberspring.utilities.MapperConverter;
import hiberspring.validators.EntityValidator;

@Transactional
@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, MapperConverter mapperConverter) {
        this.townRepository = townRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public Town createOne(TownImportFromJsonDto townImportFromJsonDto) {
        Town town = this.mapperConverter.convert(townImportFromJsonDto, Town.class);
        if (EntityValidator.isValid(town)) {
            town = this.townRepository.save(town);
            return town;
        }
        return null;
    }

    @Override
    public TownsViewDto getTowns() {
        List<Object> townWithClients = this.townRepository.getTownWithClients();
        List<TownViewDto> townViewDtos = new ArrayList<>();
        for (Object townWithClient : townWithClients) {
            Object[] objArray = (Object[]) townWithClient;
            TownViewDto townViewDto = this.mapperConverter.convert(objArray[0], TownViewDto.class);
            townViewDto.setCount((Long) objArray[1]);
            townViewDtos.add(townViewDto);
        }
        TownsViewDto townsViewDto = new TownsViewDto();
        townsViewDto.setTownViewDtos(townViewDtos);
        return townsViewDto;
    }
}
