package br.com.curso.services;

import br.com.curso.controllers.BooksController;
import br.com.curso.data.vo.v1.BooksVO;
import br.com.curso.exceptions.RequiredObjectIsNullException;
import br.com.curso.exceptions.ResourceNotFoundException;
import br.com.curso.mapper.DozerMapper;
import br.com.curso.model.Books;
import br.com.curso.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksService {

    @Autowired
    private BooksRepository repository;

    Logger logger = Logger.getLogger(BooksService.class.getName());

    public BooksVO findById(Long id) {
        logger.info("Finding a Book by id.");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found."));
        var vo = DozerMapper.parseObject(entity, BooksVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<BooksVO> findAll() {
        logger.info("Finding all Books.");
        List<Books> books = repository.findAll();
        List<BooksVO> booksVO = DozerMapper.parseListObjects(books, BooksVO.class);
        booksVO.forEach(p -> p.add(linkTo(methodOn(BooksController.class).findById(p.getKey())).withSelfRel()));
        return booksVO;
    }

    public BooksVO create(BooksVO booksVO) {
        logger.info("Creating a Book.");
        if (booksVO == null) {throw new RequiredObjectIsNullException();}
        var book = DozerMapper.parseObject(booksVO, Books.class);
        var vo = DozerMapper.parseObject(repository.save(book), BooksVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findById(booksVO.getKey())).withSelfRel());
        return vo;
    }

    public BooksVO update(BooksVO booksVO) {
        logger.info("Updating a Book.");
        if (booksVO == null) {throw new RequiredObjectIsNullException();}
        var book = repository.findById(booksVO.getKey()).orElseThrow(() -> new ResourceNotFoundException("Resource not found."));
        book.setAuthor(booksVO.getAuthor());
        book.setLaunchDate(booksVO.getLaunchDate());
        book.setPrice(booksVO.getPrice());
        book.setTitle(booksVO.getTitle());
        var vo = DozerMapper.parseObject(repository.save(book), BooksVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting a Book.");
        repository.deleteById(id);
    }
}
