package com.reto.tecnico.bcastro.application.service;

import com.reto.tecnico.bcastro.domain.model.Candidate;
import com.reto.tecnico.bcastro.domain.ports.input.CandidateService;
import com.reto.tecnico.bcastro.domain.ports.output.CandidateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository repository;

    public CandidateServiceImpl(CandidateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return repository.save(candidate);
    }

    @Override
    public void deleteCandidate(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("El candidato con el ID: " + id+" no existe.");
        }
        repository.deleteById(id);
    }
    @Override
    public Candidate finByID(Long id){
        return repository.findById(id);
    }

}