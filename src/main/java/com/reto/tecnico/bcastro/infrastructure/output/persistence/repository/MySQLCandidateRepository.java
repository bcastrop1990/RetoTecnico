package com.reto.tecnico.bcastro.infrastructure.output.persistence.repository;

import com.reto.tecnico.bcastro.domain.model.Candidate;
import com.reto.tecnico.bcastro.domain.ports.output.CandidateRepository;
import com.reto.tecnico.bcastro.infrastructure.mapper.CandidateMapper;
import com.reto.tecnico.bcastro.infrastructure.output.persistence.entity.CandidateEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MySQLCandidateRepository implements CandidateRepository {
    private final JpaCandidateRepository jpaRepository;
    private final CandidateMapper mapper;

    public MySQLCandidateRepository(JpaCandidateRepository jpaRepository, CandidateMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Candidate> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)  // Usamos el método de la entidad directamente
                .collect(Collectors.toList());
    }

    @Override
    public Candidate save(Candidate candidate) {
        CandidateEntity entity = mapper.toEntity(candidate);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);

    }

    @Override
     public boolean existsById(Long id){
        return jpaRepository.existsById(id);
    }

    @Override
    public Candidate findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found with id: " + id));
    }

}