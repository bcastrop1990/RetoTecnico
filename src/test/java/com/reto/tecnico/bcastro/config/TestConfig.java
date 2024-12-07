package com.reto.tecnico.bcastro.config;
import com.reto.tecnico.bcastro.infrastructure.mapper.CandidateMapper;
import com.reto.tecnico.bcastro.infrastructure.output.persistence.repository.JpaCandidateRepository;
import com.reto.tecnico.bcastro.infrastructure.output.persistence.repository.MySQLCandidateRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@TestConfiguration
@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class,
        SecurityFilterAutoConfiguration.class
})
@ComponentScan(basePackages = {
        "com.reto.tecnico.bcastro.infrastructure.mapper",
        "com.reto.tecnico.bcastro.infrastructure.output.persistence"
})
@EnableJpaRepositories(basePackages = "com.reto.tecnico.bcastro.infrastructure.output.persistence.repository")
@EntityScan("com.reto.tecnico.bcastro.infrastructure.output.persistence.entity")
public class TestConfig {

    @Bean
    public CandidateMapper candidateMapper() {
        return new CandidateMapper();
    }

    @Bean
    public MySQLCandidateRepository mySQLCandidateRepository(
            JpaCandidateRepository jpaCandidateRepository,
            CandidateMapper candidateMapper) {
        return new MySQLCandidateRepository(jpaCandidateRepository, candidateMapper);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")
                .build();
    }
}