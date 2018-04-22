package app.retake.services.impl;

import app.retake.domain.models.Vet;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.VetRepository;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;
    private final ModelParser modelParser;

    @Autowired
    public VetServiceImpl(final VetRepository vetRepository,
                          final ModelParser modelParser) {
        this.vetRepository = vetRepository;
        this.modelParser = modelParser;
    }

    @Override
    public <T> void create(final T dto) {
        final Vet vet = this.modelParser.convert(dto, Vet.class);
        this.vetRepository.saveAndFlush(vet);
    }
}
