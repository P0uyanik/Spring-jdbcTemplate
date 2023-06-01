/*
package de.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Configuration
public class TableofAuthorBooks {
    @Autowired final  JdbcTemplate jdbcTemplate ;

    public TableofAuthorBooks(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Bean("authorBookTable")
    void  authorBooksExecute ()
    {
        String sql = """
                CREATE TABLE IF NOT EXISTS  authorbook
                (
                idauthor BIGINT NOT NULL ,
                idbook BIGINT NOT NULL
                )
                """ ;
        jdbcTemplate.execute(sql) ;
    }
    @Bean("foreign key")
    @DependsOn("authorBookTable")
    void foreignkey()
    {
        String sql = """
                ALTER TABLE authorbook
                    ADD FOREIGN KEY (idauthor) REFERENCES author (id);
                ALTER TABLE authorbook
                    ADD FOREIGN KEY (idbook) REFERENCES book (id);
                """ ;
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("foreignkey referenced");
        }
    }

}
*/