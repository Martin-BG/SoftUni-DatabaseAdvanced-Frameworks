package com.masdefect.serviceImpl;

import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.domain.entities.Planet;
import com.masdefect.domain.entities.SolarSystem;
import com.masdefect.domain.entities.Star;
import com.masdefect.parser.interfaces.ModelParser;
import com.masdefect.repository.PlanetRepository;
import com.masdefect.repository.SolarSystemRepository;
import com.masdefect.repository.StarRepository;
import com.masdefect.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private ModelParser modelParser;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private StarRepository starRepository;

    @Autowired
    private SolarSystemRepository solarSystemRepository;

    @Override
    public void create(PlanetImportJSONDto planetImportJSONDto) {
        Planet planet = this.modelParser.convert(planetImportJSONDto, Planet.class);
        Star sun = this.starRepository.findOneByName(planet.getSun().getName());
        planet.setSun(sun);
        SolarSystem solarSystem = this.solarSystemRepository.findOneByName(planet.getSolarSystem().getName());
        planet.setSolarSystem(solarSystem);
        this.planetRepository.save(planet);
    }

    @Override
    public List<PlanetExportJSONDto> findAllPlanetsWithoutPeopleTeleportedFromThem() {
        List<Planet> planets = (List<Planet>) this.planetRepository.findAll();
        planets = planets.stream().filter(planet -> planet.getIsOriginPlanetTo().size() == 0).collect(Collectors.toList());
        List<PlanetExportJSONDto> planetExportJSONDtos = new ArrayList<>();
        for (Planet planet : planets) {
            PlanetExportJSONDto planetExportJSONDto = this.modelParser.convert(planet, PlanetExportJSONDto.class);
            planetExportJSONDtos.add(planetExportJSONDto);
        }
        return planetExportJSONDtos;
    }
}
