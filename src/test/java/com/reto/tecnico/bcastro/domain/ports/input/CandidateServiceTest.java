package com.reto.tecnico.bcastro.domain.ports.input;

import com.reto.tecnico.bcastro.application.service.CandidateServiceImpl;
import com.reto.tecnico.bcastro.domain.model.Candidate;
import com.reto.tecnico.bcastro.domain.model.Gender;
import com.reto.tecnico.bcastro.domain.ports.output.CandidateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CandidateServiceImplTest {
    @Mock
    private CandidateRepository repository;

    @InjectMocks
    private CandidateServiceImpl service;

    private Candidate testCandidate;

    @BeforeEach
    void setUp() {
        testCandidate = new Candidate(1L, "Test Name", "test@email.com", Gender.MALE,
                new BigDecimal("50000"), "123456789", "ACTIVE", LocalDateTime.now());
    }

    @Test
    void getAllCandidates_ShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(testCandidate));

        List<Candidate> result = service.getAllCandidates();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(testCandidate);
        verify(repository).findAll();
    }

    @Test
    void saveCandidate_ShouldReturnSavedCandidate() {
        when(repository.save(testCandidate)).thenReturn(testCandidate);

        Candidate result = service.saveCandidate(testCandidate);

        assertThat(result).isEqualTo(testCandidate);
        verify(repository).save(testCandidate);
    }

    @Test
    void deleteCandidate_WhenExists_ShouldDelete() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteCandidate(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deleteCandidate_WhenNotExists_ShouldThrowException() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> service.deleteCandidate(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("1");
    }

    @Test
    void findById_ShouldReturnCandidate() {
        when(repository.findById(1L)).thenReturn(testCandidate);

        Candidate result = service.finByID(1L);

        assertThat(result).isEqualTo(testCandidate);
        verify(repository).findById(1L);
    }
}