package de.service;

import de.entity.Author;
import de.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void register(String first_name, String last_name, int age) {
        //
        Author author = new Author(first_name, last_name, age);
        authorRepository.save(author);

    }

    public void delete(String first_name, String last_name) {
        Author author = new Author(first_name, last_name, 0);
        authorRepository.delete(author);
    }

    public void   authorinformation ( String first_name , String last_name)
    {
authorRepository.load(first_name , last_name);
    }

    public int idofAuthor(String first_name, String last_name , int age ) {
        Author author = new Author(first_name, last_name, age);
        return authorRepository.auathorIdLoader(author);
    }
}
