package com.smsm.backend.mapper;

import com.smsm.backend.domain.Equipe;
import com.smsm.backend.entity.EquipeEntity;
import org.springframework.stereotype.Component;

@Component
public class EquipeEntityMapper {

    public Equipe fillDomain(EquipeEntity equipeEntity) {
        Equipe equipe = new Equipe();
        equipe.setId(equipeEntity.getId());
        equipe.setNom(equipeEntity.getNom());
        equipe.setCategorie(equipeEntity.getCategorie());
        equipe.setDescription(equipeEntity.getDescription());
        return equipe;
    }

    // POTENTIELLEMENT INUTILE, A VERIFIER
    public EquipeEntity fillEntity(Equipe equipe) {
        EquipeEntity equipeEntity = new EquipeEntity();
        equipeEntity.setId(equipe.getId());
        equipeEntity.setNom(equipe.getNom());
        equipeEntity.setCategorie(equipe.getCategorie());
        equipeEntity.setDescription(equipe.getDescription());
        return equipeEntity;
    }
}
