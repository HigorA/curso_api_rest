package br.com.curso.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import br.com.curso.data.vo.v1.PersonVO;
import br.com.curso.exceptions.RequiredObjectIsNullException;
import br.com.curso.model.Person;
import br.com.curso.repositories.PersonRepository;
import br.com.curso.services.PersonService;
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService personService;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = personService.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAdress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());

    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();


        when(personRepository.findAll()).thenReturn(list);

        var people = personService.findAll();
        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());

        assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", personOne.getAdress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());


        var personFour = people.get(4);
        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());

        assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
        assertEquals("Addres Test4", personFour.getAdress());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals("Male", personFour.getGender());

        var personSeven = people.get(7);
        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());

        assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
        assertEquals("Addres Test7", personSeven.getAdress());
        assertEquals("First Name Test7", personSeven.getFirstName());
        assertEquals("Last Name Test7", personSeven.getLastName());
        assertEquals("Female", personSeven.getGender());
    }

    @Test
    void update() {
        Person entity = input.mockEntity(1);

        entity.setId(1L);

        PersonVO personVO = input.mockVO(1);
        personVO.setKey(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(personRepository.save(entity)).thenReturn(entity);

        var result = personService.update(personVO);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAdress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void createWithNullPerson() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> personService.create(null));

        String expectMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectMessage));

    }

    @Test
    void create() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        entity.setId(1L);

        PersonVO personVO = input.mockVO(1);
        personVO.setKey(1L);

        when(personRepository.save(entity)).thenReturn(entity);

        var result = personService.create(personVO);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAdress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void updateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> personService.create(null));

        String expectMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectMessage));
    }

    @Test
    void delete() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

        personService.delete(1L);
    }
}