package com.masdefect.serviceImpl;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.parser.interfaces.ModelParser;
import com.masdefect.repository.SolarSystemRepository;
import com.masdefect.service.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolarSystemServiceImpl implements SolarSystemService {

    @Autowired
    private SolarSystemRepository solarSystemRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void create(SolarSystemImportJSONDto solarSystemImportJSONDto) {
        SolarSystem solarSystem = this.modelParser.convert(solarSystemImportJSONDto, SolarSystem.class);
        this.solarSystemRepository.save(solarSystem);
    }
}
