package de.repository;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Configuration
public class TableofAuthor {
    @Autowired final JdbcTemplate jdbcTemplate ;

    public TableofAuthor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


@Bean ("AuthorTable")
    public void AuthorTableExecute () {
        String sql = """
                  CREATE TABLE IF NOT EXISTS  author 
                            (
                                    id BIGINT  GENERATED ALWAYS AS IDENTITY  PRIMARY KEY  , 
                                    first_name  VARCHAR(64) NOT NULL,
                                    last_name VARCHAR (64) NOT NULL ,
                                    age BIGINT NOT NULL 
                                 
                );""";
    jdbcTemplate.execute(sql);

}

    @Lazy
    @Bean ("destroyofauthotTable")
    public void destroy ()
{
    String sql = """
        Drop Table author
        """ ;
    jdbcTemplate.execute(sql);
}

    }
