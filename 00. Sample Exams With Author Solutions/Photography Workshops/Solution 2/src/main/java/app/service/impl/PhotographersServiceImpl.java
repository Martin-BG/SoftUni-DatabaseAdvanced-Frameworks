package app.service.impl;

import app.controller.MappingUtil;
import app.model.dto.photographers.PhotographerImportJsonDto;
import app.model.entities.BasicCamera;
import app.model.entities.Photographer;
import app.model.validation.ValidationUtil;
import app.repositories.CamerasRepository;
import app.repositories.PhotographersRepository;
import app.service.api.PhotographersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PhotographersServiceImpl implements PhotographersService {

    @Autowired
    private PhotographersRepository photographerRepository;

    @Autowired
    private CamerasRepository cameraRepository;

    @Override
    public void create(Photographer photographer) {
        if (ValidationUtil.isValid(photographer)) {
            this.photographerRepository.saveAndFlush(photographer);
            System.out.printf("Successfully imported %s %s%n", photographer.getFirstName(),
                    photographer.getLastName());
        } else {
            System.out.println("Error. Invalid data provided");
        }
    }

    @Override
    public void createMany(PhotographerImportJsonDto[] dtos) {
        List<BasicCamera> cameras = this.cameraRepository.findAll();
        for (PhotographerImportJsonDto dto : dtos) {
            Photographer photographer = MappingUtil.convert(dto, Photographer.class);
            photographer.setPrimaryCamera((BasicCamera) getRandomCamera(cameras));
            photographer.setSecondaryCamera((BasicCamera) getRandomCamera(cameras));
            this.create(photographer);
        }
    }

    @Override
    public List<Photographer> findAllOrdered() {
        List<Photographer> ordered = photographerRepository.findAllOrdered();
        return ordered;
    }

    @Override
    public List<Photographer> findAllWithSameCameras() {
        return photographerRepository.findAllWithSameCameras();
    }


    private Object getRandomCamera(List<BasicCamera> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
