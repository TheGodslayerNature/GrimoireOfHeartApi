package com.fate.GrimoireOfHeartApi.services;

import com.fate.GrimoireOfHeartApi.model.Magia.Magia;
import com.fate.GrimoireOfHeartApi.repositories.MagiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MagiaService {
    @Autowired
    private MagiaRepository magiaRepository;
    public void salvarMagia(Magia magia){
        magiaRepository.save(magia);
    }
    public Optional<Magia> getMagia(int id){
        return magiaRepository.findById(id);
    }
}
