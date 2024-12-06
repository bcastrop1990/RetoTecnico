package com.reto.tecnico.bcastro.application.dto;

import com.reto.tecnico.bcastro.domain.model.Candidate;
import com.reto.tecnico.bcastro.domain.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private BigDecimal salaryExpected;
    private String phone;
    private String status;
    private LocalDateTime createdAt;

    // Método para convertir a Domain
    public Candidate toDomain() {
        return Candidate.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .gender(Gender.valueOf(this.gender))
                .salaryExpected(this.salaryExpected)
                .phone(this.phone)
                .status(this.status)
                .createdAt(this.createdAt)
                .build();
    }

    // Método para crear desde Domain
    public static CandidateDTO fromDomain(Candidate candidate) {
        CandidateDTO dto = new CandidateDTO();
        dto.setId(candidate.getId());
        dto.setName(candidate.getName());
        dto.setEmail(candidate.getEmail());
        dto.setGender(candidate.getGender() != null ? candidate.getGender().name() : null);
        dto.setSalaryExpected(candidate.getSalaryExpected());
        dto.setPhone(candidate.getPhone());
        dto.setStatus(candidate.getStatus());
        dto.setCreatedAt(candidate.getCreatedAt());
        return dto;
    }
}