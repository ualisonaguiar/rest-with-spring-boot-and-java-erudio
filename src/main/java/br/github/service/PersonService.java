package br.github.service;

import static br.github.mapper.ObjectMapper.parseListObjects;
import static br.github.mapper.ObjectMapper.parseObject;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.github.data.dto.v1.PersonDTO;
import br.github.data.dto.v2.PersonDTOV2;
import br.github.exception.ResourceNotFoundException;
import br.github.mapper.custom.PersonMapper;
import br.github.model.Person;
import br.github.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper converter;

    public List<PersonDTO> findAll() {
        log.info("Finding All Person!");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(final Long id) {

        log.info("Finding one Person!");

        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return parseObject(person, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO personDTO) {
        log.info("Saving one Person!");
        var person = repository.save(parseObject(personDTO, Person.class));

        return parseObject(person, PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 personDTO) {
        log.info("Saving one Person V2!");
        var entity = converter.convertDTOToEntity(personDTO);

        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO personDTO) {
        log.info("updating one Person!");

        findById(personDTO.getId());

        Person person = repository.save(parseObject(personDTO, Person.class));

        return parseObject(person, PersonDTO.class);
    }

    public void delete(final Long id) {
        log.info("deleting one Person!");

        var personDTO = findById(id);

        repository.delete(parseObject(personDTO, Person.class));
    }

}
