package app.retake.services.impl;

import app.retake.domain.dto.*;
import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import app.retake.domain.models.Vet;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.ProcedureRepository;
import app.retake.services.api.AnimalAidService;
import app.retake.services.api.AnimalService;
import app.retake.services.api.ProcedureService;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final ModelParser modelParser;
    private final AnimalAidService animalAidService;
    private final VetService vetService;
    private final AnimalService animalService;

    @Autowired
    public ProcedureServiceImpl(final ProcedureRepository procedureRepository,
                                final ModelParser modelParser,
                                final AnimalAidService animalAidService,
                                final VetService vetService,
                                final AnimalService animalService) {
        this.procedureRepository = procedureRepository;
        this.modelParser = modelParser;
        this.animalAidService = animalAidService;
        this.vetService = vetService;
        this.animalService = animalService;
    }

    @Override
    public <T> void create(final T dto) {
        ProcedureXMLImportDTO d = (ProcedureXMLImportDTO) dto;
        final Animal animal = this.animalService.findByPassportNumber(d.getPassportNumber());
        final Vet vet = this.vetService.findByName(d.getVetName());
        if (animal == null || vet == null) {
            throw new IllegalArgumentException();
        }

        final Set<AnimalAid> services = new HashSet<>();
        for (final ProcedureAnimalAidXMLImportDTO aid : d.getServices()) {
            final AnimalAid animalAid = this.animalAidService.findByName(aid.getName());
            if (animalAid == null) {
                throw new IllegalArgumentException();
            }
            services.add(animalAid);
        }

        final Procedure procedure = new Procedure();
        procedure.setAnimal(animal);
        procedure.setDate(d.getDate());
        procedure.setVet(vet);
        procedure.setServices(services);

        this.procedureRepository.saveAndFlush(procedure);
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        final ProcedureWrapperXMLExportDTO procedures = new ProcedureWrapperXMLExportDTO();
        procedures.setProcedures(
                this.procedureRepository
                        .findAll()
                        .stream()
                        .map(procedure -> {
                            final ProcedureXMLExportDTO dto = new ProcedureXMLExportDTO();
                            dto.setOwner(procedure.getAnimal().getPassport().getOwnerPhoneNumber());
                            dto.setPassport(procedure.getAnimal().getPassport().getSerialNumber());
                            dto.setAids(procedure.getServices()
                                    .stream()
                                    .map(animalAid -> this.modelParser
                                            .convert(animalAid, ProcedureAnimalAidXMLExportDTO.class))
                                    .collect(Collectors.toList()));
                            return dto;
                        })
                        .collect(Collectors.toList()));

        return procedures;
    }
}

