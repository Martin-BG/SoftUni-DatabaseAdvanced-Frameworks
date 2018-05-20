package com.masdefect.serviceImpl;

import com.masdefect.domain.dto.json.PersonExportJSONDto;
import com.masdefect.domain.dto.json.PersonImportJSONDto;
import com.masdefect.domain.entities.Person;
import com.masdefect.domain.entities.Planet;
import com.masdefect.parser.interfaces.ModelParser;
import com.masdefect.repository.PersonRepository;
import com.masdefect.repository.PlanetRepository;
import com.masdefect.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private ModelParser modelParser;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public void create(PersonImportJSONDto personImportJSONDto) {
        Person person = this.modelParser.convert(personImportJSONDto, Person.class);
        Planet homePlanet = this.planetRepository.findOneByName(personImportJSONDto.getHomePlanetName());
        person.setHomePlanet(homePlanet);
        this.personRepository.save(person);
    }

    @Override
    public List<PersonExportJSONDto> findInnocentPersons() {
        List<Person> persons = this.personRepository.findInnocentPersons();
        List<PersonExportJSONDto> personExportJSONDtos = new ArrayList<>();
        for (Person person : persons) {
            PersonExportJSONDto personExportJSONDto = this.modelParser.convert(person, PersonExportJSONDto.class);
            personExportJSONDtos.add(personExportJSONDto);
        }

        return personExportJSONDtos;
    }
}
