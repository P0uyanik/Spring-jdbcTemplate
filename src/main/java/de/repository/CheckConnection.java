package de.repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class CheckConnection {
        private  final DataSource dataSource ;
        public CheckConnection(DataSource dataSource) {
            this.dataSource = dataSource;
        }
        @Bean
        public void checkmethode() {
            try {
                System.out.println(dataSource.getConnection().getClass().getSimpleName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }


