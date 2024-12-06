package com.reto.tecnico.bcastro.infrastructure.input.rest;

import com.reto.tecnico.bcastro.application.dto.CandidateDTO;
import com.reto.tecnico.bcastro.domain.model.Candidate;
import com.reto.tecnico.bcastro.domain.model.Gender;
import com.reto.tecnico.bcastro.domain.ports.input.CandidateService;
import com.reto.tecnico.bcastro.infrastructure.mapper.CandidateMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateControllerTest {


    @Mock
    private CandidateService candidateService;

    @Mock
    private CandidateMapper mapper;

    @InjectMocks
    private CandidateController candidateController;

    private CandidateDTO candidateDTO;
    private Candidate candidate;

    @BeforeEach
    void setUp() {
        // Inicializar datos de prueba
        candidateDTO = new CandidateDTO();
        candidateDTO.setId(1L);
        candidateDTO.setName("Juan Pérez");
        candidateDTO.setEmail("juan@email.com");
        candidateDTO.setGender("MALE");
        candidateDTO.setSalaryExpected(new BigDecimal("50000"));
        candidateDTO.setPhone("999888777");
        candidateDTO.setStatus("ACTIVE");

        candidate = new Candidate(
                1L,
                "Juan Pérez",
                "juan@email.com",
                Gender.MALE,
                new BigDecimal("50000"),
                "999888777",
                "ACTIVE",
                LocalDateTime.now()
        );
    }

    @Test
    void getAllCandidates_ShouldReturnList() {
        // Arrange
        List<Candidate> candidates = List.of(candidate);
        when(candidateService.getAllCandidates()).thenReturn(candidates);
        when(mapper.toDTO(candidate)).thenReturn(candidateDTO);

        // Act
        ResponseEntity<List<CandidateDTO>> response = candidateController.getAllCandidates();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(candidateDTO, response.getBody().get(0));
    }

    @Test
    void createCandidate_ShouldReturnCreatedCandidate() {
        // Arrange
        when(mapper.toDomain(candidateDTO)).thenReturn(candidate);
        when(candidateService.saveCandidate(candidate)).thenReturn(candidate);
        when(mapper.toDTO(candidate)).thenReturn(candidateDTO);

        // Act
        ResponseEntity<CandidateDTO> response = candidateController.createCandidate(candidateDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candidateDTO, response.getBody());
    }

    @Test
    void deleteCandidate_ShouldReturnNoContent() {
        // Arrange
        Long id = 1L;
        doNothing().when(candidateService).deleteCandidate(id);

        // Act
        ResponseEntity<Void> response = candidateController.deleteCandidate(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(candidateService).deleteCandidate(id);
    }

    @Test
    void buscarCandidateById_ShouldReturnCandidate() {
        // Arrange
        Long id = 1L;
        when(candidateService.finByID(id)).thenReturn(candidate);
        when(mapper.toDTO(candidate)).thenReturn(candidateDTO);

        // Act
        ResponseEntity<CandidateDTO> response = candidateController.buscarCandidateById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candidateDTO, response.getBody());
    }

    @Test
    void buscarCandidateById_WhenNotFound_ShouldThrowException() {
        // Arrange
        Long id = 999L;
        when(candidateService.finByID(id)).thenThrow(new EntityNotFoundException("Candidate not found"));

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () ->
                candidateController.buscarCandidateById(id)
        );
    }

}