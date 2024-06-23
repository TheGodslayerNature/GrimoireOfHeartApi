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
    //Criar as devidas exceções

    @Autowired
    private final PersonagemRepository personagemRepository;
    public Personagem salvarPersonagem(Personagem personagem){
        return personagemRepository.save(personagem);
    }
    public Optional<Personagem> getPersonagemPorId(long id){
      return personagemRepository.findById(id);
    }
    public List<Personagem> getPersonagens(){
        if (personagemRepository.findAll().isEmpty()){
            System.out.println("Não existem personagens");
        }
        return personagemRepository.findAll();
    }
    public Personagem atualizarPersonagem(Personagem novasCaracteristicas, long personagemId){
        Personagem personagem = personagemRepository.getReferenceById(personagemId);
        personagem.setNomePersonagem(novasCaracteristicas.getNomePersonagem());
        return personagem;
    }
    public void deletarPersonagem(long idPersonagem) throws Exception {
        if (!personagemRepository.existsById(idPersonagem)){
            System.out.println("Personagem não existe");
            return;
        }
        personagemRepository.findById(idPersonagem).ifPresent(personagemRepository::delete);
    }

    public void deletarTodos() {
        personagemRepository.deleteAll();
    }
}
