package com.fate.GrimoireOfHeartApi.services;

import com.fate.GrimoireOfHeartApi.model.persona.Persona;
import com.fate.GrimoireOfHeartApi.repositories.PersonaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PersonaService {
    //Arrumar a Atualização de persona e de personagem

    @Autowired
    private final PersonaRepository personaRepository;

    public Persona salvarPersona(Persona persona){
        return personaRepository.save(persona);
    }
    public Persona getPersona(Long personaId){
        return personaRepository.getReferenceById(personaId);
    }

    public Persona atualizarPersona(Persona personaNovasCaracteristicas, Long personaId){
        //Arrumar depois, de uma forma que não utilize o set para o ID
        Persona persona = personaRepository.getReferenceById(personaId);
        BeanUtils.copyProperties(personaNovasCaracteristicas, persona);
        persona.setId(personaId);
        return personaRepository.save(persona);
    }

    public List<Persona> listarPersonas(){
        return personaRepository.findAll();
    }
    public void deletarPersona(Long personaId){
        if (personaRepository.existsById(personaId)) {
            Persona persona = personaRepository.getReferenceById(personaId);
            personaRepository.delete(persona);
        }
    }

}
