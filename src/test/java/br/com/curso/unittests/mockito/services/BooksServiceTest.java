package br.com.curso.unittests.mockito.services;

import br.com.curso.data.vo.v1.BooksVO;
import br.com.curso.model.Books;
import br.com.curso.repositories.BooksRepository;
import br.com.curso.services.BooksService;
import br.com.curso.unittests.mapper.mocks.MockBook;
import br.com.curso.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BooksServiceTest {

    MockBook input;

    @InjectMocks
    private BooksService service;

    @Mock
    BooksRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Books books = input.mockEntity(1);
        books.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(books));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/books/v1/1>;rel=\"self\"]"));
        assertEquals("Author test1", result.getAuthor());
        assertEquals("Title test1", result.getTitle());
        assertEquals(1.0,result.getPrice());
        assertNull(result.getLaunchDate());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {}

    @Test
    void update() {}

    @Test
    void delete() {}
}
