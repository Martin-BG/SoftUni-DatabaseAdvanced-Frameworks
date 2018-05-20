package com.masdefect.serviceImpl;

import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.domain.entities.Star;
import com.masdefect.parser.interfaces.ModelParser;
import com.masdefect.repository.SolarSystemRepository;
import com.masdefect.repository.StarRepository;
import com.masdefect.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl implements StarService {

    @Autowired
    private StarRepository starRepository;

    @Autowired
    private SolarSystemRepository solarSystemRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void create(StarImportJSONDto starImportJSONDto) {
        Star star = this.modelParser.convert(starImportJSONDto, Star.class);
        SolarSystem solarSystem = this.solarSystemRepository.findOneByName(star.getSolarSystem().getName());
        star.setSolarSystem(solarSystem);
        this.starRepository.save(star);
    }
}
