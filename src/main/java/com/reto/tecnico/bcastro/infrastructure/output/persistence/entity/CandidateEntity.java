package com.reto.tecnico.bcastro.infrastructure.output.persistence.entity;

import com.reto.tecnico.bcastro.domain.model.Candidate;
import com.reto.tecnico.bcastro.domain.model.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidates")
@Data
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "salary_expected")
    private BigDecimal salaryExpected;

    private String phone;
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Método para convertir a Domain
    public Candidate toDomain() {
        return new Candidate(
                this.id,
                this.name,
                this.email,
                this.gender,
                this.salaryExpected,
                this.phone,
                this.status,
                this.createdAt
        );
    }

    // Método para crear desde Domain
    public static CandidateEntity fromDomain(Candidate candidate) {
        CandidateEntity entity = new CandidateEntity();
        entity.setId(candidate.getId());
        entity.setName(candidate.getName());
        entity.setEmail(candidate.getEmail());
        // ... resto de campos
        return entity;
    }
}