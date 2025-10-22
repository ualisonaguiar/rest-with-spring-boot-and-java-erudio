package br.github.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.github.controllers.docs.BookControllerDocs;
import br.github.data.dto.v1.BookDTO;
import br.github.model.Book;
import br.github.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Tag(name = "Book", description = "Endpoints for Manging Book")
@RequestMapping("/api/book/v1")
public class BookController implements BookControllerDocs {

    private final BookService service;

    @GetMapping()
    public List<BookDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO bookDTO) {
        return service.create(bookDTO);
    }

    @PutMapping("/{id}")
    public BookDTO update(@RequestBody BookDTO bookDTO, @PathVariable("id") final Long id) {
        bookDTO.setId(id);
        return service.update(bookDTO);
    }
}
