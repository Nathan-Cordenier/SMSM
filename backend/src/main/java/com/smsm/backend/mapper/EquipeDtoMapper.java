package com.smsm.backend.mapper;

import com.smsm.backend.domain.Equipe;
import com.smsm.backend.dto.EquipeDto;
import org.springframework.stereotype.Component;

@Component
public class EquipeDtoMapper {
    // POTENTIELLEMENT INUTILE, A VERIFIER
    public Equipe fillDomain(EquipeDto equipeDto) {
        Equipe equipe = new Equipe();
        equipe.setId(equipeDto.getId());
        equipe.setNom(equipeDto.getNom());
        equipe.setCategorie(equipeDto.getCategorie());
        equipe.setDescription(equipeDto.getDescription());
        return equipe;
    }

    public EquipeDto fillDto(Equipe equipe) {
        EquipeDto equipeDto = new EquipeDto();
        equipeDto.setId(equipe.getId());
        equipeDto.setNom(equipe.getNom());
        equipeDto.setCategorie(equipe.getCategorie());
        equipeDto.setDescription(equipe.getDescription());
        return equipeDto;
    }
}
