package com.fate.GrimoireOfHeartApi.services;

import com.fate.GrimoireOfHeartApi.model.Personagem.Personagem;
import com.fate.GrimoireOfHeartApi.repositories.PersonagemRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PersonagemService {
    @Autowired
    private final PersonagemRepository personagemRepository;

    public void salvarPersonagem(Personagem personagem){
        personagemRepository.saveAndFlush(personagem);
    }
    public Optional<Personagem> getPersonagemPorId(int id){
      return personagemRepository.findById(id);
    }
}
