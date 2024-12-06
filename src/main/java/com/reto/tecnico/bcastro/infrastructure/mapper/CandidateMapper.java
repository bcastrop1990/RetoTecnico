package com.reto.tecnico.bcastro.infrastructure.mapper;

import com.reto.tecnico.bcastro.application.dto.CandidateDTO;
import com.reto.tecnico.bcastro.domain.model.Candidate;
import com.reto.tecnico.bcastro.domain.model.Gender;
import com.reto.tecnico.bcastro.infrastructure.output.persistence.entity.CandidateEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CandidateMapper {

    public CandidateDTO toDTO(Candidate domain) {
        return CandidateDTO.fromDomain(domain);
    }

    public CandidateEntity toEntity(Candidate domain) {
        return CandidateEntity.fromDomain(domain);
    }

    public Candidate toDomain(CandidateEntity entity) {
        return entity.toDomain();
    }

    public Candidate toDomain(CandidateDTO dto) {
        return new Candidate(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getGender() != null ? Gender.valueOf(dto.getGender()) : null,
        //Gender.valueOf(dto.getGender()),
                dto.getSalaryExpected(),
                dto.getPhone(),
                dto.getStatus(),
                dto.getCreatedAt()
        );
    }
}