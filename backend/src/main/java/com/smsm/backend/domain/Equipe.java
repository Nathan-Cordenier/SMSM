package com.smsm.backend.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipe {
    private Long id;
    private String nom;
    private String categorie;
    private String description;
}
