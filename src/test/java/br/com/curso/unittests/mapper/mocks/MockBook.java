package br.com.curso.unittests.mapper.mocks;

import br.com.curso.data.vo.v1.BooksVO;
import br.com.curso.model.Books;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockBook {

    public Books mockEntity() {
        return mockEntity(0);
    }

    public BooksVO mockVO() {
        return mockVO(0);
    }

    public Books mockEntity(Integer number) {
        Books books = new Books();
        books.setId(number.longValue());
        books.setAuthor("Author test" + number);
        books.setPrice(number.doubleValue());
        books.setLaunchDate(LocalDate.now());
        books.setTitle("Title test" + number);
        return books;
    }

    public BooksVO mockVO(Integer number) {
        BooksVO books = new BooksVO();
        books.setKey(number.longValue());
        books.setAuthor("Author test" + number);
        books.setPrice(number.doubleValue());
        books.setLaunchDate(LocalDate.now());
        books.setTitle("Title test" + number);
        return books;
    }

    public List<Books> mockEntityList() {
        List<Books> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BooksVO> mockVOList() {
        List<BooksVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
}
