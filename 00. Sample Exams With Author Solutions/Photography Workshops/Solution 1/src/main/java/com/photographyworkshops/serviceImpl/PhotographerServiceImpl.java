package com.photographyworkshops.serviceImpl;

import com.photographyworkshops.domain.dto.photographers.*;
import com.photographyworkshops.domain.entities.cameras.BasicCamera;
import com.photographyworkshops.domain.entities.cameras.DSLRCamera;
import com.photographyworkshops.domain.entities.lens.Lens;
import com.photographyworkshops.domain.entities.photographers.Photographer;
import com.photographyworkshops.parser.interfaces.ModelParser;
import com.photographyworkshops.repository.AccessoryRepository;
import com.photographyworkshops.repository.BasicCameraRepository;
import com.photographyworkshops.repository.LenRepository;
import com.photographyworkshops.repository.PhotographerRepository;
import com.photographyworkshops.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class PhotographerServiceImpl implements PhotographerService {

    @Autowired
    private ModelParser modelParser;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private LenRepository lenRepository;

    @Autowired
    private BasicCameraRepository basicCameraRepository;

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Override
    public int create(PhotographerImportJSONDto photographerDto) {
        Photographer photographer = this.modelParser.convert(photographerDto, Photographer.class);
        Set<Lens> lenses =  this.lenRepository.findByIdIn(photographerDto.getLenses());
        BasicCamera primaryCamera = this.getRandomCamera();
        photographer.setPrimaryCamera(primaryCamera);
        BasicCamera secondaryCamera = this.getRandomCamera();
        photographer.setSecondaryCamera(secondaryCamera);
        for (Lens lens : lenses) {
            lens.setPhotographer(photographer);
        }

        return lenses.size();
    }

    @Override
    public List<PhotographerExportJSONDto> findAllOrder() {
        List<Photographer> photographers = this.photographerRepository.findAllOrder();
        List<PhotographerExportJSONDto> photographerExportJSONDtos = new ArrayList<>();
        for (Photographer photographer : photographers) {
            PhotographerExportJSONDto photographerExportJSONDto = this.modelParser.convert(photographer, PhotographerExportJSONDto.class);
            photographerExportJSONDtos.add(photographerExportJSONDto);
        }

        return photographerExportJSONDtos;
    }

    @Override
    public List<PhotographerLandscapeExportJSONDto> findAllWithLenses() {
        List<Photographer> photographers = this.photographerRepository.findAllWithLenses();
        List<PhotographerLandscapeExportJSONDto> photographerLandscapeExportJSONDtos = new ArrayList<>();
        for (Photographer photographer : photographers) {
            if(photographer.getPrimaryCamera() instanceof DSLRCamera) {
                PhotographerLandscapeExportJSONDto photographerLandscapeExportJSONDto = this.modelParser.convert(photographer, PhotographerLandscapeExportJSONDto.class);
                photographerLandscapeExportJSONDto.setCount(photographer.getLenses().size());
                photographerLandscapeExportJSONDtos.add(photographerLandscapeExportJSONDto);
            }
        }

        return photographerLandscapeExportJSONDtos;
    }

    @Override
    public PhotographersCameraExportXML findAllWithCameras() {
        List<Photographer> photographers = this.photographerRepository.findAllWithCameras();
        List<PhotographerCameraExportXML> photographerCameraExportXMLs = new ArrayList<>();
        for (Photographer photographer : photographers) {
            PhotographerCameraExportXML photographerCameraExportXML = this.modelParser.convert(photographer, PhotographerCameraExportXML.class);
            photographerCameraExportXMLs.add(photographerCameraExportXML);
        }

        PhotographersCameraExportXML photographersCameraExportXML = new PhotographersCameraExportXML();
        photographersCameraExportXML.setPhotographers(photographerCameraExportXMLs);
        return photographersCameraExportXML;
    }

    private BasicCamera getRandomCamera(){
        long cameraCount = this.basicCameraRepository.count();
        long randomId = ThreadLocalRandom.current().nextLong(1, cameraCount + 1);
        BasicCamera basicCamera = this.basicCameraRepository.findOne(randomId);
        return basicCamera;
    }
}
