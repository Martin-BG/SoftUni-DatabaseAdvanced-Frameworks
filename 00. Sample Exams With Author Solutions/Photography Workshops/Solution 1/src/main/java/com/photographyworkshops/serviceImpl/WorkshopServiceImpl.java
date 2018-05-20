package com.photographyworkshops.serviceImpl;

import com.photographyworkshops.domain.dto.photographers.PhotographerImportXMLDto;
import com.photographyworkshops.domain.dto.photographers.PhotographersLocationExportXMLDto;
import com.photographyworkshops.domain.dto.workshops.WorkshopExportXMLDto;
import com.photographyworkshops.domain.dto.workshops.WorkshopImportXMLDto;
import com.photographyworkshops.domain.dto.workshops.WorkshopLocationExportXMLDto;
import com.photographyworkshops.domain.dto.workshops.WorkshopLocationsExportXMLDto;
import com.photographyworkshops.domain.entities.photographers.Photographer;
import com.photographyworkshops.domain.entities.workshops.Workshop;
import com.photographyworkshops.parser.interfaces.ModelParser;
import com.photographyworkshops.repository.PhotographerRepository;
import com.photographyworkshops.repository.WorkshopRepository;
import com.photographyworkshops.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class WorkshopServiceImpl implements WorkshopService {


    @Autowired
    private ModelParser modelParser;

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Override
    public void create(WorkshopImportXMLDto workshopImportXMLDto) {
        Workshop workshop = this.modelParser.convert(workshopImportXMLDto, Workshop.class);
        List<PhotographerImportXMLDto> photographerImportXMLDtoList = workshopImportXMLDto.getPhotographers();
        workshop.getPhotographers().clear();
        String[] names = workshopImportXMLDto.getTrainer().split("\\s+");
        String trainerFirstName = names[0];
        String trainerLastName = names[1];
        Photographer trainer = this.photographerRepository.findOneByFirstNameAndLastName(trainerFirstName, trainerLastName);
        workshop.setTrainer(trainer);
        for (PhotographerImportXMLDto photographerImportXMLDto : photographerImportXMLDtoList) {
            Photographer photographer = this.photographerRepository.findOneByFirstNameAndLastName(photographerImportXMLDto.getFirstName(), photographerImportXMLDto.getLastName());
            workshop.addPhotographer(photographer);
        }

        this.workshopRepository.save(workshop);
    }

    @Override
    public WorkshopLocationsExportXMLDto findAllByLocation() {
        WorkshopLocationsExportXMLDto workshopLocationsExportXMLDto = new WorkshopLocationsExportXMLDto();
        List<Workshop> workshops = this.workshopRepository.findAllByLocation();
        for (Workshop workshop : workshops) {
            WorkshopExportXMLDto workshopExportXMLDto = new WorkshopExportXMLDto();
            Set<Photographer> photographers = workshop.getPhotographers();
            PhotographersLocationExportXMLDto photographersLocationExportXMLDto = new PhotographersLocationExportXMLDto();
            for (Photographer photographer : photographers) {
                photographersLocationExportXMLDto.getPhotographers().add(photographer.getName());
            }

            photographersLocationExportXMLDto.setCount(workshop.getPhotographers().size());
            workshopExportXMLDto.setPhotographers(photographersLocationExportXMLDto);
            workshopExportXMLDto.setName(workshop.getName());
            workshopExportXMLDto.setTotalProfit(photographersLocationExportXMLDto.getCount() * workshop.getPricePerParticipant().doubleValue() * 0.8);
            if (workshopLocationsExportXMLDto.getLocations().size() > 0 && workshopLocationsExportXMLDto.getLocations().stream().filter(l -> l.getName().equals(workshop.getLocation())).findAny().orElse(null) != null) {
                workshopLocationsExportXMLDto.getLocations()
                        .stream()
                        .filter(l -> l.getName()
                                .equals(workshop.getLocation()))
                        .findFirst()
                        .get()
                        .getWorkshops()
                        .add(workshopExportXMLDto);
            } else {
                WorkshopLocationExportXMLDto workshopLocationExportXMLDto = new WorkshopLocationExportXMLDto();
                workshopLocationExportXMLDto.setName(workshop.getLocation());
                workshopLocationExportXMLDto.getWorkshops().add(workshopExportXMLDto);
                workshopLocationsExportXMLDto.getLocations().add(workshopLocationExportXMLDto);
            }
        }

        return workshopLocationsExportXMLDto;
    }
}
