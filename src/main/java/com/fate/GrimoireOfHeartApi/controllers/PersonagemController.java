package com.fate.GrimoireOfHeartApi.controllers;

import com.fate.GrimoireOfHeartApi.model.Personagem.Personagem;
import com.fate.GrimoireOfHeartApi.services.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {
    @Autowired
    private PersonagemService personagemService;
    @GetMapping("/buscarPersonagem/{id}")
    public Optional<Personagem> getPersonagem(@PathVariable(value = "id") int idPersonagem){
        return personagemService.getPersonagemPorId(idPersonagem);
    }
    @PostMapping("/salvarPersonagem")
    public void salvarPersonagem(@RequestBody Personagem personagem){
        this.personagemService.salvarPersonagem(personagem);
    }
}
