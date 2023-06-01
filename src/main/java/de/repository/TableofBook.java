package de.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Configuration
public class TableofBook {
    @Autowired  JdbcTemplate jdbcTemplate ;
    @Bean("bookTable")
    public  void bookTableExecute ()
    {

        String sql = """
                CREATE TABLE IF NOT EXISTS book
                (
                    id_Author BIGINT NOT NULL,
                    title VARCHAR (64) NOT NULL,
                    yearOfPublication  TIMESTAMP NOT NULL
                ) ;
                
                """ ;
        jdbcTemplate.execute(sql) ;
    }

    @Bean
    @DependsOn("bookTable")
     void ForeignKeyMaker()
    {
        String sql = """
                ALTER TABLE book
                    ADD FOREIGN KEY (id_Author) REFERENCES author (id);
                """ ;
        jdbcTemplate.execute(sql);
    }


    @Lazy
    @Bean
    public void destroy ()
    {
        String sql = """
        Drop Table author
        """ ;
        jdbcTemplate.execute(sql);
    }


}
