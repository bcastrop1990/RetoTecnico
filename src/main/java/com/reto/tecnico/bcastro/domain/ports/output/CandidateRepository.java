package com.reto.tecnico.bcastro.domain.ports.output;

import com.reto.tecnico.bcastro.domain.model.Candidate;

import java.util.List;

public interface CandidateRepository {
    List<Candidate> findAll();

    Candidate save(Candidate candidate);

    void deleteById(Long id);

    boolean existsById(Long id);

    Candidate findById(Long id);
}