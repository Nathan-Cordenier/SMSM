package com.smsm.backend.controller;

import com.smsm.backend.domain.Equipe;
import com.smsm.backend.dto.EquipeDto;
import com.smsm.backend.mapper.EquipeDtoMapper;
import com.smsm.backend.service.EquipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipes")
@RequiredArgsConstructor
public class EquipeRestController {
    private final EquipeService equipeService;
    private final EquipeDtoMapper equipeDtoMapper;

    @GetMapping
    public ResponseEntity<List<EquipeDto>> findAll() {
        List<EquipeDto> equipes = equipeService.findAll()
                .stream()
                .map(equipeDtoMapper::fillDto)
                .toList();
        return ResponseEntity.ok(equipes); // HTTP 200 + body
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeDto> findById(@PathVariable Long id){
        Optional<Equipe> optionalEquipe = equipeService.findById(id);

        return optionalEquipe.map(equipe -> ResponseEntity.ok(equipeDtoMapper.fillDto(equipe))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EquipeDto> createEquipe(@RequestBody EquipeDto equipeDto){
        Equipe equipe = equipeDtoMapper.fillDomain(equipeDto);
        Equipe equipeSaved = equipeService.createEquipe(equipe);
        return ResponseEntity.status(201).body(equipeDtoMapper.fillDto(equipeSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeDto> updateEquipe(@PathVariable Long id, @RequestBody EquipeDto equipeDto) {
        Equipe equipe = equipeDtoMapper.fillDomain(equipeDto);
        Optional<Equipe> equipeUpdated = equipeService.updateEquipe(id, equipe);
        return equipeUpdated
                .map(updatedEquipe -> ResponseEntity.ok(equipeDtoMapper.fillDto(updatedEquipe)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable Long id){
        return equipeService.deleteEquipe(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
