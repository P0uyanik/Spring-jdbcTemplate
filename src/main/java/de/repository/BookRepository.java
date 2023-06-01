package de.repository;

import de.entity.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.stream.Stream;

@Repository
public class BookRepository {
    @Autowired
    final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Book book) {
        String sql = """
                INSERT INTO book VALUES (? , ? , ? )
                """;
        Object[] objects = {book.authorId(), book.title(), book.yearOfPublication()};
        jdbcTemplate.update(sql, objects);

    }

    public Stream <String>  load(String title) {
        String sql =
                """     
                               SELECT  * FROM book
                                INNER JOIN author
                                ON book.id_author = author.id
                                WHERE title = ?
                        """;
        Stream<String> stringStream = jdbcTemplate.queryForList(sql, title).stream().map(
                rs -> {
                    record Informations(String title, LocalDate yearOfPublication, String first_name,
                                        String last_name) {
                    }
                    return new Informations("" + rs.get("title"), ((Timestamp) rs.get("yearOfPublication")).toLocalDateTime().toLocalDate(), "" + rs.get("first_name"), "" + rs.get("last_name"));
                }
        ).map(data -> data.first_name + " , " + data.last_name + " ," + data.title() + " , " + data.yearOfPublication());


        return stringStream ;
//

    }

    public void delete(Book book) {
        String sql = """
                DELETE FROM book
                WHERE title = ?
                """;
        int update = jdbcTemplate.update(sql, book.title());
        if (update > 0)
            System.out.println("deleted");
        else
            System.err.println("not found");
    }


    public void bookTableDelete() {
        String sql = """
                DROP TABLE book
                """;
        jdbcTemplate.execute(sql);
    }


}
