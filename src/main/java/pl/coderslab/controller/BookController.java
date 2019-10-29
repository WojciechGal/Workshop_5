package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.Book;
import pl.coderslab.domain.JsonResponse;
import pl.coderslab.exceptions.ServiceException;
import pl.coderslab.service.BookService;

import java.util.List;

@RestController //restcontroller ma w sobie response body!!!
@RequestMapping("/books")
public class BookController {

    @RequestMapping(value = "/hello", produces = "application/json")
    public String hello(){
        return "{\"hello\": \"World\"}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook(){
        return new Book(1L,"9788324631766","Thinking in Java",
                "Bruce Eckel","Helion","programming");
    }

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @RequestMapping(value = "", produces = "application/json")
    public List<Book> findAll(){
        return service.findAll();
    }

    @RequestMapping("/{id}")
    public Book findById(@PathVariable long id){
        return service.findById(id);
    }

    @RequestMapping(value = "/create", produces = "application/json")
    public Book create(@RequestBody Book book) {
        return service.create(book);
    }


    ///delete

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResponse delete(@PathVariable long id){
        try {
            if (service.delete(service.findById(id))) {
                return new JsonResponse(200, "Succesfully removed book: " + id);
            } else {
                return new JsonResponse(500, "Cannot remove book");
            }
        }catch (ServiceException e) {
            return new JsonResponse(500, "Cannot reomove book");
        }
    }


    //update

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Book update(@PathVariable long id, @RequestBody Book book) {
        book.setId(id);
        return service.update(book);
    }







}
