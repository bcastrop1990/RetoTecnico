package com.reto.tecnico.bcastro.infrastructure.output.persistence.repository;

import com.reto.tecnico.bcastro.domain.model.Candidate;
import com.reto.tecnico.bcastro.infrastructure.output.persistence.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCandidateRepository extends JpaRepository<CandidateEntity, Long> {
    Optional<Candidate> findByEmail(String email);
}
