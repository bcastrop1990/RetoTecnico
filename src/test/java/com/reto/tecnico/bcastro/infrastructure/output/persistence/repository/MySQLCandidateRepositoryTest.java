package com.reto.tecnico.bcastro.infrastructure.output.persistence.repository;

import com.reto.tecnico.bcastro.domain.model.Candidate;
import com.reto.tecnico.bcastro.domain.model.Gender;
import com.reto.tecnico.bcastro.infrastructure.mapper.CandidateMapper;
import com.reto.tecnico.bcastro.infrastructure.output.persistence.entity.CandidateEntity;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.reto.tecnico.bcastro")
class MySQLCandidateRepositoryTest {


    @Autowired
    private JpaCandidateRepository jpaRepository;

    @Autowired
    private CandidateMapper mapper;

    @Autowired
    private TestEntityManager entityManager;

    private MySQLCandidateRepository repository;
    private CandidateEntity testEntity;

    @BeforeEach
    void setUp() {
        repository = new MySQLCandidateRepository(jpaRepository, mapper);
        testEntity = new CandidateEntity();
        testEntity.setName("Test Name");
        testEntity.setEmail("test@email.com");
        testEntity.setGender(Gender.MALE);
        testEntity.setSalaryExpected(new BigDecimal("50000"));
        testEntity.setPhone("123456789");
        testEntity.setStatus("ACTIVE");
        testEntity.setCreatedAt(LocalDateTime.now());
    }

    @Test
    @Transactional
    @Sql(statements = "DELETE FROM candidates", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findAll_ShouldReturnAllCandidates() {
        entityManager.persist(testEntity);
        entityManager.flush();

        List<Candidate> results = repository.findAll();

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo(testEntity.getName());
    }

    @Test
    void save_ShouldPersistAndReturnCandidate() {
        Candidate candidate = mapper.toDomain(testEntity);

        Candidate saved = repository.save(candidate);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo(candidate.getName());
        assertThat(saved.getEmail()).isEqualTo(candidate.getEmail());
    }

    @Test
    void findById_WhenExists_ShouldReturnCandidate() {
        CandidateEntity saved = entityManager.persist(testEntity);
        entityManager.flush();

        Candidate found = repository.findById(saved.getId());

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(testEntity.getName());
    }

    @Test
    void findById_WhenNotExists_ShouldThrowException() {
        assertThatThrownBy(() -> repository.findById(999L))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void deleteById_ShouldRemoveCandidate() {
        CandidateEntity saved = entityManager.persist(testEntity);
        entityManager.flush();

        repository.deleteById(saved.getId());

        assertThat(entityManager.find(CandidateEntity.class, saved.getId()))
                .isNull();
    }
}