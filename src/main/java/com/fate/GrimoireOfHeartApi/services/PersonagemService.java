package com.fate.GrimoireOfHeartApi.services;

import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
import com.fate.GrimoireOfHeartApi.repositories.PersonagemRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PersonagemService {
    //Devem ser adicionadas regras de negócio
    @Autowired
    private final PersonagemRepository personagemRepository;
    public void salvarPersonagem(Personagem personagem){
        personagemRepository.save(personagem);
    }
    public Optional<Personagem> getPersonagemPorId(int id){
      return personagemRepository.findById(id);
    }
    public List<Personagem> getPersonagens(){
        return personagemRepository.findAll();
    }
    public void atualizarPersonagem(int idPersonagem, Personagem novasCaracteristicas){
        Optional<Personagem> actualPersonagem = personagemRepository.findById(idPersonagem);
        actualPersonagem.ifPresent(personagem -> personagem.setNomeJogador(novasCaracteristicas.getNomeJogador()));
        actualPersonagem.ifPresent(personagem -> personagem.setNomePersonagem(novasCaracteristicas.getNomePersonagem()));
    }
    public void deletarPersonagem(int idPersonagem){
        personagemRepository.findById(idPersonagem).ifPresent(personagemRepository::delete);
    }
}
