package com.reto.tecnico.bcastro.domain.model;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Candidate {
    private Long id;
    private String name;
    private String email;
    private Gender gender;
    private BigDecimal salaryExpected;
    private String phone;
    private String status;
    private LocalDateTime createdAt;

}


