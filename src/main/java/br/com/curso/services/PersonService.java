package br.com.curso.services;

import br.com.curso.exceptions.ResourceNotFoundException;
import br.com.curso.model.Person;
import br.com.curso.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(Long id) {
        logger.info("Finding a Person");
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
    }

    public List<Person> findAll() {
        logger.info("Finding All");
        return repository.findAll();
    }

    public void create(Person person) {
        logger.info("Creating a register!");
        repository.save(person);
    }

    public void update(Person person) {
        logger.info("Updating a register!");
        var entity = findById(person.getId());

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        repository.save(person);
    }

    public void delete(Long id) {
        logger.info("Deleting a register...");
        var entity = findById(id);
        repository.delete(entity);
    }
}
