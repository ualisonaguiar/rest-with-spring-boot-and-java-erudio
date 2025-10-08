package br.github.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.github.data.dto.v1.PersonDTO;
import br.github.service.PersonService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/person/v1")
public class PersonController {

    private final PersonService service;

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable("id") Long id) {
        var person = service.findById(id);

        person.setBirthDay(new Date());
        person.setPhoneNumber("+5561986011959");
        person.setSensitiveData("Foo Bar");

        return person;
    }

    @PostMapping
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);
    }

    // @PostMapping("/v2")
    // public PersonDTOV2 create(@RequestBody PersonDTOV2 person) {
    // return service.createV2(person);
    // }

    @PutMapping
    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
