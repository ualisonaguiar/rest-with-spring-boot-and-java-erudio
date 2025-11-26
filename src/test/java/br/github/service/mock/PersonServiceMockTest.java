package br.github.service.mock;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.github.data.dto.v1.PersonDTO;
import br.github.model.Person;
import br.github.repository.PersonRepository;
import br.github.service.PersonService;

@ExtendWith(MockitoExtension.class)
class PersonServiceMockTest {

    private static final AtomicLong COUNTER = new AtomicLong(0);

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    // @Test
    // @Disabled("REASON: Still Under Developtment")
    // void findAll() {
    //     List<Person> persons = new ArrayList<>();
    //     for (int i = 0; i <= 20; i++) {
    //         persons.add(generateEntity());
    //     }
    //     when(repository.findAll()).thenReturn(persons);

    //     PageRequest pageable = PageRequest.of(1, 12);

    //     Page<PersonDTO> personDb = service.findAll(pageable);

    //     assertEquals(persons.size(), personDb.getContent().size());
    // }

    @Test
    void inserir() {
        Person person = generateEntity();

        when(repository.findById(person.getId())).thenReturn(Optional.of(person));

        assertTrue(repository.findById(person.getId()).isPresent());
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
