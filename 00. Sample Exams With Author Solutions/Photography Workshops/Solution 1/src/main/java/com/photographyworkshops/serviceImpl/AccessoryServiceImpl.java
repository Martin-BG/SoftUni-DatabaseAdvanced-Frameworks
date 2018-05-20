package com.photographyworkshops.serviceImpl;

import com.photographyworkshops.domain.dto.accessoaries.AccessoryImportXMLDto;
import com.photographyworkshops.domain.entities.accessories.Accessory;
import com.photographyworkshops.domain.entities.photographers.Photographer;
import com.photographyworkshops.parser.interfaces.ModelParser;
import com.photographyworkshops.repository.AccessoryRepository;
import com.photographyworkshops.repository.PhotographerRepository;
import com.photographyworkshops.service.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class AccessoryServiceImpl implements AccessoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void create(AccessoryImportXMLDto accessoryDto) {
        Accessory accessory = this.modelParser.convert(accessoryDto, Accessory.class);
        this.accessoryRepository.save(accessory);
        accessory.setPhotographer(this.getRandomPhotographer());
    }

    private Photographer getRandomPhotographer(){
        long photographerCount = this.photographerRepository.count();
        long randomId = ThreadLocalRandom.current().nextLong(1, photographerCount + 1);
        Photographer photographer = this.photographerRepository.findOne(randomId);
        return photographer;
    }
}
