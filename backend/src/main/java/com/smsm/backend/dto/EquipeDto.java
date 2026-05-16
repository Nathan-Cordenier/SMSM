package com.smsm.backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipeDto {
    private Long id;
    private String nom;
    private String categorie;
    private String description;
}
