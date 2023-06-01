package de.repository;

import de.entity.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;

@Repository
public class AuthorRepository {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    final JdbcTemplate jdbcTemplate;

    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void showAuthorTable()
    {

        String sql = """
                SELECT * From author
                """ ;

    }
    public void save(Author author) // az service
    {

        var sql = """
                INSERT INTO author ( first_name , last_name , age  ) VALUES ( ? , ? , ?  ) 
                """;
        var first_name = author.firstName();
        var last_name = author.lastName();
        var age = author.age();
        Object[] objects = {first_name, last_name, age};
        jdbcTemplate.update(sql, objects);
        logger.info("Author {}  added", first_name);
    }

    public void delete(Author author) // az service
    {
        String sql = """
                DELETE FROM author 
                WHERE first_name = ? AND last_name = ? 
                """;
        Object[] objects = {author.firstName(), author.lastName()};
        int update = jdbcTemplate.update(sql, objects);
if (update > 0)
    logger.info("Author {}  deleted", author.firstName());
else
    System.err.println("Not Found");

    }
    public  int auathorIdLoader (Author author)
    {

        String sql = """
                SELECT id from author 
                WHERE first_name = ? AND last_name = ? AND age = ? 
                """ ;
        Object [] objects = {author.firstName() , author.lastName() , author.age()} ;
        Integer integer = null ;
        while (true)
        {
            try {
                integer = jdbcTemplate.queryForObject(sql, objects, Integer.class);
                break;
            } catch (DataAccessException e) {
                save(author) ;
            }
        }

        return  integer ;
    }
    public  void load(String first_name , String last_name)
    {
        String sql =
                """
                           SELECT  * FROM book
                            INNER JOIN author
                            ON book.id_author = author.id
                            WHERE first_name = ? AND last_name = ?     
                        """ ;
        Object [] objects = {first_name , last_name } ;
        jdbcTemplate.queryForList(sql , objects ).stream().map(
                rs -> {
                    record Informations (String title , LocalDate yearOfPublication , String first_name , String last_name) {}
                    return new Informations (""+rs.get("title") , ((Timestamp)rs.get("yearOfPublication")).toLocalDateTime().toLocalDate(),  ""+rs.get("first_name") ,""+rs.get("last_name") ) ;
                }
        ).map(data ->data.first_name +" , " +data.last_name + " ,"+ data.title() + " , " + data.yearOfPublication() ).forEach(System.out::println);
    }
}
