package com.reto.tecnico.bcastro.infrastructure.input.rest;

import com.reto.tecnico.bcastro.application.dto.CandidateDTO;
import com.reto.tecnico.bcastro.domain.model.Candidate;
import com.reto.tecnico.bcastro.domain.ports.input.CandidateService;
import com.reto.tecnico.bcastro.infrastructure.mapper.CandidateMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidates")
public class CandidateController {
    private final CandidateService candidateService;
    private final CandidateMapper mapper;

    public CandidateController(CandidateService candidateService, CandidateMapper mapper) {
        this.candidateService = candidateService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getAllCandidates() {
        return ResponseEntity.ok(
                candidateService.getAllCandidates()
                        .stream()
                        .map(mapper::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<CandidateDTO> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        Candidate candidate = mapper.toDomain(candidateDTO);
        Candidate saved = candidateService.saveCandidate(candidate);
        return ResponseEntity.ok(mapper.toDTO(saved));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable(name = "id") Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<CandidateDTO> buscarCandidateById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(mapper.toDTO(candidateService.finByID(id)));
    }

}