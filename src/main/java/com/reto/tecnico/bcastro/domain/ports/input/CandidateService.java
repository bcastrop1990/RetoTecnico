package com.reto.tecnico.bcastro.domain.ports.input;

import com.reto.tecnico.bcastro.domain.model.Candidate;

import java.util.List;

public interface CandidateService {
    List<Candidate> getAllCandidates();
    Candidate saveCandidate(Candidate candidate);
    void deleteCandidate(Long id);
    Candidate finByID(Long id);
}