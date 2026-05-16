package com.smsm.backend.service;

import com.smsm.backend.dao.EquipeDao;
import com.smsm.backend.domain.Equipe;
import com.smsm.backend.entity.EquipeEntity;
import com.smsm.backend.mapper.EquipeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipeService {
    private final EquipeDao equipeDao;
    private final EquipeEntityMapper equipeEntityMapper;

    public List<Equipe> findAll(){
        return equipeDao.findAll().stream().map(equipeEntityMapper::fillDomain).toList();
    }

    public Optional<Equipe> findById(Long id){
        Optional<EquipeEntity> equipeEntity = equipeDao.findById(id);

        if(equipeEntity.isEmpty()) {
            return Optional.empty();
        }
        Equipe equipe = equipeEntityMapper.fillDomain(equipeEntity.get());
        return Optional.of(equipe);
    }

    public Equipe createEquipe(Equipe equipe){
        EquipeEntity equipeEntity = equipeEntityMapper.fillEntity(equipe);
        EquipeEntity equipeSaved = equipeDao.save(equipeEntity);
        return equipeEntityMapper.fillDomain(equipeSaved);
    }

    public Optional<Equipe> updateEquipe(Long id, Equipe equipe){
        if(equipeDao.findById(id).isEmpty()){
            return Optional.empty();
        }
        equipe.setId(id);
        EquipeEntity equipeEntity = equipeEntityMapper.fillEntity(equipe);
        EquipeEntity equipeUpdated = equipeDao.save(equipeEntity);
        return Optional.of(equipeEntityMapper.fillDomain(equipeUpdated));
    }

    public boolean deleteEquipe(Long id){
        if(equipeDao.findById(id).isEmpty()){
            return false;
        }
        equipeDao.deleteById(id);
        return true;
    }
}
