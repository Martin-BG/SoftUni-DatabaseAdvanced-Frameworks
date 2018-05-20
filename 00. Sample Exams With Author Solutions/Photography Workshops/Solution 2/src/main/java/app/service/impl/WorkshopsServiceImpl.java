package app.service.impl;

import app.model.entities.Photographer;
import app.model.entities.Workshop;
import app.model.validation.ValidationUtil;
import app.repositories.PhotographersRepository;
import app.repositories.WorkshopsRepository;
import app.service.api.WorkshopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class WorkshopsServiceImpl implements WorkshopsService {

    @Autowired
    private WorkshopsRepository workshopsDao;

    @Autowired
    private PhotographersRepository photographersDao;

    @Override
    public Workshop addWorkshop(Workshop workshop) {
        Workshop persistedWorkshop = null;
        if (ValidationUtil.isValid(workshop)) {
            Photographer trainer = photographersDao.findByFirstNameAndLastName(workshop.getTrainer().getFirstName(), workshop.getTrainer().getLastName());
            workshop.setTrainer(trainer);

            Set<Photographer> participants = new HashSet<>();
            if (workshop.getParticipants() != null) {
                for (Photographer photographer : workshop.getParticipants()) {
                    Photographer participant = photographersDao.findByFirstNameAndLastName(photographer.getFirstName(), photographer.getLastName());
                    participants.add(participant);
                }
                workshop.setParticipants(participants);
            }


            if (ValidationUtil.isValid(workshop)) {
                persistedWorkshop = workshopsDao.save(workshop);
                System.out.println("Successfully imported " + workshop.getName());
            } else {
                System.out.println("Error. Invalid data provided");
            }

        } else {
            System.out.println("Error. Invalid data provided");
        }
        return persistedWorkshop;
    }

}
