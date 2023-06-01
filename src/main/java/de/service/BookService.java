package de.service;

import java.time.LocalDate;
import java.util.stream.Stream;

import de.entity.Book ;
import de.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired final
    BookRepository bookRepository ;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void addBook ( int id , String title , LocalDate yearOfPublication )
    {
        Book book = new Book(id, title, yearOfPublication);
        bookRepository.save(book);
    }

    public void deletebook  (String title )
    {
        bookRepository.delete(new Book(0 , title , null));
    }

    public void  bookinformation (String title)
    {
        Stream<String> load = bookRepository.load(title);
        load.forEach(System.out::println);
    }

}
