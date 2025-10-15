package br.github.mapper.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.github.data.dto.v1.PersonDTO;
import br.github.model.Person;
import br.github.repository.PersonRepository;
import br.github.service.PersonService;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private static final AtomicLong COUNTER = new AtomicLong(0);

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    @Test
    void findAll() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            persons.add(generateEntity());
        }

        when(repository.findAll()).thenReturn(persons);

        List<PersonDTO> personDb = service.findAll();
        assertEquals(persons.size(), personDb.size());
    }

    Person generateEntity() {
        Long id = COUNTER.incrementAndGet();

        Person person = new Person();
        person.setAddress("Brasília - DF");
        person.setFirstName("João Paulo - " + id);
        person.setGender("M");
        person.setId(id);
        person.setLastName("Rodrigues");

        return person;
    }

}
