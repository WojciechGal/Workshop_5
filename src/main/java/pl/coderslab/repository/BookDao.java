package pl.coderslab.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import pl.coderslab.domain.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class BookDao {

    private final List<Book> list;
    //lista jest finalna a nie jej zawartość!!!

    public BookDao() {
        this.list = new ArrayList<>();
        list.add(new Book(1L,"9788324631766","Thinking in Java", "Bruce Eckel","Helion","programming"));
    }

}
