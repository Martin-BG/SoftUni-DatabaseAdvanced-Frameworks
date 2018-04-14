package app.serviceImpl;

import app.domain.dto.PersonDto;
import app.domain.model.Person;
import app.repository.PersonRepository;
import app.service.PersonService;
import app.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void create(Person person) {
        this.personRepository.saveAndFlush(person);
    }

    @Override
    public void create(final PersonDto personDto) {
        Person person = getPersonFromPersonDto(personDto);
        this.personRepository.saveAndFlush(person);
    }

    private Person getPersonFromPersonDto(final PersonDto personDto) {
        Person person = ObjectMapper.getInstance().map(personDto, Person.class);
        person.getPhoneNumbers().forEach(phoneNumber -> phoneNumber.setPerson(person));
        return person;
    }

    @Override
    public Person findById(long id) {
        return this.personRepository.findById(id).orElse(null);
    }

    @Override
    public PersonDto getPersonDtoFromId(final long id) {
        Person person = this.findById(id);
        if (person != null) {
            return ObjectMapper.getInstance().map(person, PersonDto.class);
        }
        return null;
    }

    @Override
    public List<Person> findByCountry(String country) {
        return this.personRepository.findByCountry(country);
    }
}
