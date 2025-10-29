package br.github.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.github.data.dto.v1.PersonDTO;
import br.github.mapper.ObjectMapper;
import br.github.model.Person;
import br.github.repository.PersonRepository;
import br.github.testcontainers.AbstractIntegrationTest;

@SpringBootTest
class PersonServiceTest extends AbstractIntegrationTest {

    private static final AtomicLong COUNTER = new AtomicLong(0);

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonService service;

    @Test
    void deveInserirPessoa() {
        PersonDTO criado = service.create(criarPessoaDTO());

        assertNotNull(criado.getId());
        assertTrue(repository.findById(criado.getId()).isPresent());
    }

    @Test
    void deveListarPessoas() {
        PersonDTO criado = criarPessoaNoBanco();

        List<Person> pessoas = repository.findAll();

        boolean existe = pessoas.stream().anyMatch(p -> p.getId().equals(criado.getId()));
        assertTrue(existe);
    }

    @Test
    void deveAtualizarPessoa() {
        PersonDTO criado = criarPessoaNoBanco();

        criado.setFirstName("Ualison");
        criado.setLastName("Aguiar");

        PersonDTO atualizado = service.update(criado);
        Person encontrado = repository.findById(atualizado.getId()).orElseThrow();

        assertEquals(criado.getFirstName(), encontrado.getFirstName());
        assertEquals(criado.getLastName(), encontrado.getLastName());
    }

    @Test
    void deveExcluirPessoa() {
        PersonDTO criado = criarPessoaNoBanco();

        service.delete(criado.getId());

        boolean aindaExiste = repository.findById(criado.getId()).isPresent();
        assertFalse(aindaExiste);
    }

    private PersonDTO criarPessoaDTO() {
        Person entity = gerarEntidadeFake();
        PersonDTO dto = ObjectMapper.parseObject(entity, PersonDTO.class);
        dto.setId(null);
        return dto;
    }

    private PersonDTO criarPessoaNoBanco() {
        return service.create(criarPessoaDTO());
    }

    private Person gerarEntidadeFake() {
        long id = COUNTER.incrementAndGet();
        Person p = new Person();
        p.setAddress("Brasília - DF");
        p.setFirstName("João Paulo - " + id);
        p.setGender("M");
        p.setId(id);
        p.setLastName("Rodrigues");
        return p;
    }
}
