package com.smsm.backend.controller;

import com.smsm.backend.dto.EquipeDto;
import com.smsm.backend.mapper.EquipeDtoMapper;
import com.smsm.backend.service.EquipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
