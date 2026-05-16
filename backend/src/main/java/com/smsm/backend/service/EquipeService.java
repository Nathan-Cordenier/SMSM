package com.smsm.backend.service;

import com.smsm.backend.dao.EquipeDao;
import com.smsm.backend.domain.Equipe;
import com.smsm.backend.mapper.EquipeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeService {
    private final EquipeDao equipeDao;
    private final EquipeEntityMapper equipeEntityMapper;

    public List<Equipe> findAll(){
        return equipeDao.findAll().stream().map(equipeEntityMapper::fillDomain).toList();
    }
}
