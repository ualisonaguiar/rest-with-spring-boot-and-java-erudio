package br.github.service;

import static br.github.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.github.controllers.PersonController;
import br.github.data.dto.v1.PersonDTO;
import br.github.data.dto.v2.PersonDTOV2;
import br.github.exception.BadRequestException;
import br.github.exception.FileStorageException;
import br.github.exception.ResourceNotFoundException;
import br.github.file.importer.contract.FileImporter;
import br.github.file.importer.factory.FileImporterFactory;
import br.github.mapper.custom.PersonMapper;
import br.github.model.Person;
import br.github.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper converter;
    private final PagedResourcesAssembler<PersonDTO> assembler;
    private final FileImporterFactory fileImporter;

    public PagedModel<EntityModel<PersonDTO>> findAll(Pageable pageable) {
        log.info("Finding All Person!");
        Page<Person> people = repository.findAll(pageable);
        return buildPagedModel(pageable, people);
    }

    @Cacheable(value = "persons", key = "#id")
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

    public List<PersonDTO> massCreation(MultipartFile file) {
        log.info("Importing People from file!");

        if (file.isEmpty()) {
            throw new BadRequestException("Please set a Valid File!");
        }

        try (InputStream inputStream = file.getInputStream()) {
            String filename = Optional.ofNullable(file.getOriginalFilename())
                    .orElseThrow(() -> new BadRequestException("File name cannot be null"));

            FileImporter importer = this.fileImporter.getImporter(filename);

            List<Person> entitys = importer.importFiles(inputStream).stream()
                    .map(dto -> repository.save(parseObject(dto, Person.class)))
                    .toList();

            return entitys.stream().map(entity -> {
                PersonDTO dto = parseObject(entity, PersonDTO.class);
                addHateoasLinks(dto);

                return dto;
            }).toList();
        } catch (Exception e) {
            throw new FileStorageException("Error procecssing the file!");
        }
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

    @Transactional
    public PersonDTO disablePerson(final Long id) {
        log.info("Disabling one Person!");

        findById(id);

        repository.disablePerson(id);

        var dto = parseObject(findById(id), PersonDTO.class);
        addHateoasLinks(dto);

        return dto;
    }

    public PagedModel<EntityModel<PersonDTO>> findByName(String firstName, Pageable pageable) {
        log.info("Finding All Person!");

        Page<Person> people = repository.findPeopleByName(firstName, pageable);

        return buildPagedModel(pageable, people);
    }

    private PagedModel<EntityModel<PersonDTO>> buildPagedModel(Pageable pageable, Page<Person> people) {
        var peopleWithLinks = people.map(person -> {
            PersonDTO personDTO = parseObject(person, PersonDTO.class);
            addHateoasLinks(personDTO);

            return personDTO;
        });

        Link findAllLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(PersonController.class)
                        .findAll(
                                pageable.getPageNumber(),
                                pageable.getPageSize(),
                                String.valueOf(pageable.getSort())))
                .withSelfRel();

        return assembler.toModel(peopleWithLinks, findAllLink);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        dto.add(linkTo(methodOn(PersonController.class).findAll(1, 12, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findByName("", 1, 12, "asc")).withRel("findByName")
                .withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class)).slash("massCreation").withRel("massCreation")
                .withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("put").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("patch").withType("PATCH"));
    }
}
