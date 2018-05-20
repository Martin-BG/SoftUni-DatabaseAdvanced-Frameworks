package app.service.impl;

import app.controller.MappingUtil;
import app.model.dto.accessories.AccessoryXMLDto;
import app.model.entities.Accessory;
import app.model.entities.Photographer;
import app.repositories.AccessoriesRepository;
import app.repositories.PhotographersRepository;
import app.service.api.AccessoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class AccessoriesServiceImpl implements AccessoriesService {

    private AccessoriesRepository accessoriesRepository;
    private PhotographersRepository photographersRepository;

    @Autowired
    public AccessoriesServiceImpl(AccessoriesRepository accessoriesRepository, PhotographersRepository photographersRepository) {
        this.accessoriesRepository = accessoriesRepository;
        this.photographersRepository = photographersRepository;
    }

    @Override
    public void create(Accessory accessory) {
        this.accessoriesRepository.save(accessory);
    }

    @Override
    public void createMany(AccessoryXMLDto[] dtos) {
        List<Photographer> photographers = this.photographersRepository.findAll();
        for (AccessoryXMLDto dto : dtos) {
            Accessory accessory = MappingUtil.convert(dto, Accessory.class);
            accessory.setOwner((Photographer) getRandomPhotographer(photographers));
            this.create(accessory);
        }
    }

    private Object getRandomPhotographer(List<Photographer> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
