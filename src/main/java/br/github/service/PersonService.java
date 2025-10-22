package br.github.service;

import static br.github.mapper.ObjectMapper.parseListObjects;
import static br.github.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.stereotype.Service;

import br.github.controllers.PersonController;
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

        var persons = parseListObjects(repository.findAll(), PersonDTO.class);

        persons.forEach(this::addHateoasLinks);
        return persons;
    }

    public PersonDTO findById(final Long id) {

        log.info("Finding one Person!");

        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        var dto = parseObject(person, PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public PersonDTO create(PersonDTO personDTO) {
        log.info("Saving one Person!");
        var person = repository.save(parseObject(personDTO, Person.class));

        var dto = parseObject(person, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
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

        var dto = parseObject(person, PersonDTO.class);

        addHateoasLinks(dto);
        return dto;
    }

    public void delete(final Long id) {
        log.info("deleting one Person!");

        var personDTO = findById(id);

        repository.delete(parseObject(personDTO, Person.class));
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("put").withType("PUT"));
    }
}
