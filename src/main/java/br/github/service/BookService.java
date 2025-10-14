package br.github.service;

import static br.github.mapper.ObjectMapper.parseListObjects;
import static br.github.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.github.controllers.BookController;
import br.github.data.dto.v1.BookDTO;
import br.github.data.dto.v1.PersonDTO;
import br.github.exception.ResourceNotFoundException;
import br.github.model.Book;
import br.github.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    public List<BookDTO> findAll() {
        log.info("Finding All Books!");
        var books = parseListObjects(repository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks);
        return books;
    }

    public BookDTO findById(final Long id) {

        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        var bookDTO = parseObject(book, BookDTO.class);

        addHateoasLinks(bookDTO);

        return bookDTO;
    }

    public void delete(final Long id) {
        BookDTO dto = this.findById(id);
        Book book = parseObject(dto, Book.class);
        repository.delete(book);
    }

    public BookDTO create(BookDTO bookDTO) {
        Book book = parseObject(bookDTO, Book.class);
        repository.save(book);

        bookDTO = parseObject(book, BookDTO.class);
        addHateoasLinks(bookDTO);

        return bookDTO;
    }

    public BookDTO update(BookDTO bookDTO) {
        Book book = parseObject(bookDTO, Book.class);
        findById(book.getId());
        repository.save(book);

        bookDTO = parseObject(book, BookDTO.class);
        addHateoasLinks(bookDTO);

        return bookDTO;
    }    

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto, dto.getId())).withRel("put").withType("PUT"));
    }
}
