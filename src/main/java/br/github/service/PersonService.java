package br.github.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.github.exception.ResourceNotFoundException;
import br.github.model.Person;
import br.github.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private final PersonRepository repository;

    public List<Person> findAll() {
        log.info("Finding All Person!");

        return repository.findAll();
    }

    public Person findById(final Long id) {

        log.info("Finding one Person!");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Person create(Person person) {
        log.info("Saving one Person!");
        repository.save(person);

        return person;
    }

    public Person update(Person person) {
        log.info("updating one Person!");

        Person entity = findById(person.getId());

        entity.setFirtName(person.getFirtName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        repository.save(entity);

        return entity;
    }

    public void delete(final Long id) {
        log.info("deleting one Person!");

        repository.delete(findById(id));
    }

}
