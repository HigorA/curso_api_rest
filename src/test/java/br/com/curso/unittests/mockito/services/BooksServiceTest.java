package br.com.curso.unittests.mockito.services;

import br.com.curso.data.vo.v1.BooksVO;
import br.com.curso.exceptions.RequiredObjectIsNullException;
import br.com.curso.model.Books;
import br.com.curso.repositories.BooksRepository;
import br.com.curso.services.BooksService;
import br.com.curso.unittests.mapper.mocks.MockBook;
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
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void findAll() {
        List<Books> list = input.mockEntityList();


        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();
        assertNotNull(people);
        assertEquals(14, people.size());

        var bookOne = people.get(1);
        assertNotNull(bookOne);
        assertNotNull(bookOne.getKey());
        assertNotNull(bookOne.getLinks());

        assertTrue(bookOne.toString().contains("[</api/books/v1/1>;rel=\"self\"]"));
        assertEquals("Author test1", bookOne.getAuthor());
        assertEquals("Title test1", bookOne.getTitle());
        assertEquals(1, bookOne.getPrice());
        assertNotNull(bookOne.getLaunchDate());


        var bookFour = people.get(4);
        assertNotNull(bookFour);
        assertNotNull(bookFour.getKey());
        assertNotNull(bookFour.getLinks());

        assertTrue(bookFour.toString().contains("[</api/books/v1/4>;rel=\"self\"]"));
        assertEquals("Author test4", bookFour.getAuthor());
        assertEquals("Title test4", bookFour.getTitle());
        assertEquals(4, bookFour.getPrice());
        assertNotNull(bookFour.getLaunchDate());

        var bookSeven = people.get(7);
        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getKey());
        assertNotNull(bookSeven.getLinks());

        assertTrue(bookSeven.toString().contains("[</api/books/v1/7>;rel=\"self\"]"));
        assertEquals("Author test7", bookSeven.getAuthor());
        assertEquals("Title test7", bookSeven.getTitle());
        assertEquals(7, bookSeven.getPrice());
        assertNotNull(bookSeven.getLaunchDate());
    }

    @Test
    void update() {
        Books entity = input.mockEntity(1);

        entity.setId(1L);

        BooksVO bookVO = input.mockVO(1);
        bookVO.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);

        var result = service.update(bookVO);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("[</api/books/v1/1>;rel=\"self\"]"));
        assertEquals("Author test1", result.getAuthor());
        assertEquals("Title test1", result.getTitle());
        assertEquals(1.0,result.getPrice());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void createWithNullBooks() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.create(null));

        String expectMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectMessage));

    }

    @Test
    void create() {
        Books entity = input.mockEntity(1);
        entity.setId(1L);

        Books persisted = entity;
        persisted.setId(1L);

        BooksVO bookVO = input.mockVO(1);
        bookVO.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(bookVO);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("[</api/books/v1/1>;rel=\"self\"]"));
        assertEquals("Author test1", result.getAuthor());
        assertEquals("Title test1", result.getTitle());
        assertEquals(1.0,result.getPrice());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void updateWithNullBooks() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.create(null));

        String expectMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectMessage));
    }

    @Test
    void delete() {
        Books entity = input.mockEntity(1);
        entity.setId(1L);


        service.delete(1L);
    }
}
