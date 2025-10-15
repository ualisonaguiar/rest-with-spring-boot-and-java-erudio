package br.github.mapper.service;

import static br.github.mapper.ObjectMapper.parseObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.github.data.dto.v1.BookDTO;
import br.github.exception.ResourceNotFoundException;
import br.github.model.Book;
import br.github.repository.BookRepository;
import br.github.service.BookService;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private static final AtomicLong COUNTER = new AtomicLong(0);

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @Test
    void findAll() {

        List<Book> books = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            books.add(generateEntity());
        }

        when(repository.findAll()).thenReturn(books);
        List<BookDTO> result = service.findAll();
        assertEquals(books.size(), result.size());

        for (int i = 0; i < books.size(); i++) {
            Book expected = books.get(i);
            Book actual = parseObject(result.get(i), Book.class);

            assertEquals(expected.getAuthor(), actual.getAuthor());
            assertEquals(expected.getTitle(), actual.getTitle());
            assertEquals(expected.getPrice(), actual.getPrice());
            assertEquals(expected.getLaunchDate(), actual.getLaunchDate());
        }

        verify(repository).findAll();
    }

    @Test
    void create() {
        Book book = generateEntity();
        book.setId(null);
        when(repository.save(any(Book.class))).thenReturn(book);

        BookDTO bookDTO = service.create(parseObject(book, BookDTO.class));

        assertEqualsEntityToDTO(book, bookDTO);

        verify(repository).save(any(Book.class));
    }

    @Test
    void findById() {
        Book book = generateEntity();
        when(repository.findById(book.getId())).thenReturn(Optional.of(book));

        BookDTO bookDTO = service.findById(book.getId());

        assertEqualsEntityToDTO(book, bookDTO);
    }

    @Test
    void update() {
        Book book = generateEntity();

        when(repository.findById(book.getId())).thenReturn(Optional.of(book));
        when(repository.save(any(Book.class))).thenAnswer(i -> i.getArgument(0));

        Book book2 = generateEntity();
        book2.setId(book.getId());

        BookDTO result = service.update(parseObject(book2, BookDTO.class));

        assertEqualsEntityToDTO(book2, result);
    }

    @Test
    void delete() {
        Book book = generateEntity();

        when(repository.findById(book.getId())).thenReturn(Optional.of(book));
        doNothing().when(repository).delete(book);

        service.delete(book.getId());

        verify(repository).findById(book.getId());
        verify(repository).delete(book);
    }

    void findByIdNotFound() {
        Long id = 999L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(id);
        });
    }

    Book generateEntity() {
        Long id = COUNTER.incrementAndGet();

        Book book = new Book();
        book.setAuthor("Ualison Aguiar-" + id);
        book.setId(id);
        book.setLaunchDate(Date.valueOf("2025-10-14"));
        book.setPrice(BigDecimal.valueOf(100.12));
        book.setTitle("PHP e MySQL-" + id);

        return book;
    }

    void assertEqualsEntityToDTO(Book book, BookDTO bookDTO) {
        assertEquals(book.getAuthor(), bookDTO.getAuthor());
        assertEquals(book.getTitle(), bookDTO.getTitle());
        assertEquals(book.getPrice(), bookDTO.getPrice());
        assertEquals(book.getLaunchDate(), bookDTO.getLaunchDate());
    }

}
