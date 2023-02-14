package br.com.curso.services;

import br.com.curso.data.vo.v1.PersonVO;
import br.com.curso.exceptions.ResourceNotFoundException;
import br.com.curso.mapper.DozerMapper;
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

    public PersonVO findById(Long id) {
        logger.info("Finding a Person");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding All");
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating a register!");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = repository.save(entity);
        return DozerMapper.parseObject(vo, PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating a register!");

        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Not Found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());


        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting a register...");
        var entity = findById(id);
        repository.delete(DozerMapper.parseObject(entity, Person.class));
    }
}
