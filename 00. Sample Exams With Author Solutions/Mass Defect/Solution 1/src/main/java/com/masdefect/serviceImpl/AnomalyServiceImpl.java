package com.masdefect.serviceImpl;

import com.masdefect.domain.dto.json.AnomalyExportJSONDto;
import com.masdefect.domain.dto.json.AnomalyImpotJSONDto;
import com.masdefect.domain.dto.json.AnomalyVictimsJSONDto;
import com.masdefect.domain.dto.xml.AnomaliesXMLDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.domain.entities.Anomaly;
import com.masdefect.domain.entities.Person;
import com.masdefect.domain.entities.Planet;
import com.masdefect.parser.interfaces.ModelParser;
import com.masdefect.repository.AnomalyRepository;
import com.masdefect.repository.PersonRepository;
import com.masdefect.repository.PlanetRepository;
import com.masdefect.service.AnomalyService;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AnomalyServiceImpl implements AnomalyService {

    @Autowired
    private AnomalyRepository anomalyRepository;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void create(AnomalyImpotJSONDto anomalyImpotJSONDto) {
        Anomaly anomaly = this.modelParser.convert(anomalyImpotJSONDto, Anomaly.class);
        Planet teleportPlanet = this.planetRepository.findOneByName(anomalyImpotJSONDto.getTeleportPlanetName());
        anomaly.setTeleportPlanet(teleportPlanet);
        Planet orignPlanet = this.planetRepository.findOneByName(anomalyImpotJSONDto.getOriginPlanetName());
        anomaly.setOriginPlanet(orignPlanet);
        this.anomalyRepository.save(anomaly);
    }

    @Override
    public void create(AnomalyVictimsJSONDto anomalyVictimsDto) {
        Anomaly anomaly = this.anomalyRepository.findOne(anomalyVictimsDto.getId());
        Person person = this.personRepository.findOneByName(anomalyVictimsDto.getPerson());
        anomaly.addPerson(person);
    }

    @Override
    public void create(AnomalyXMLDto anomalyImportXMLDto) {
        Anomaly anomaly = this.modelParser.convert(anomalyImportXMLDto, Anomaly.class);
        Planet teleportPlanet = this.planetRepository.findOneByName(anomalyImportXMLDto.getTeleportPlanetName());
        anomaly.setTeleportPlanet(teleportPlanet);
        Planet orignPlanet = this.planetRepository.findOneByName(anomalyImportXMLDto.getOriginPlanetName());
        anomaly.setOriginPlanet(orignPlanet);
        Set<Person> persons = anomaly.getPersons();
        anomaly.getPersons().clear();
        for (Person person : persons) {
            Person victim = this.personRepository.findOneByName(person.getName());
            anomaly.addPerson(victim);
        }

        this.anomalyRepository.save(anomaly);
    }

    @Override
    public AnomalyExportJSONDto findMostAffectingAnomalies() {
        List<Anomaly> anomalies = this.anomalyRepository.findMostAffectingAnomalies();
        Anomaly anomaly = anomalies.stream().findFirst().get();
        PropertyMap<Anomaly, AnomalyExportJSONDto> propertyMap = new PropertyMap<Anomaly, AnomalyExportJSONDto>() {
            @Override
            protected void configure() {
                map(source.getPersons().size(), destination.getVictimsCount());
            }
        };

        AnomalyExportJSONDto anomalyExportJSONDto = this.modelParser.convert(anomaly, AnomalyExportJSONDto.class, propertyMap);
        return anomalyExportJSONDto;
    }

    @Override
    public AnomaliesXMLDto finaAllAnomalies() {
        List<Anomaly> anomalies = this.anomalyRepository.finaAllAnomalies();
        AnomaliesXMLDto anomaliesXMLDto = new AnomaliesXMLDto();
        for (Anomaly anomaly : anomalies) {
            AnomalyXMLDto anomalyXMLDto = this.modelParser.convert(anomaly, AnomalyXMLDto.class);
            anomaliesXMLDto.add(anomalyXMLDto);
        }

        return anomaliesXMLDto;
    }
}
