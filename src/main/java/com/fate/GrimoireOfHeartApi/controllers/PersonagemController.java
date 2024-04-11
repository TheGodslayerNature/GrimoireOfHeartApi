package com.fate.GrimoireOfHeartApi.controllers;

import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
import com.fate.GrimoireOfHeartApi.services.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ResponseBody
@RequestMapping("/personagem")
public class PersonagemController {
    @Autowired
    private PersonagemService personagemService;
    @GetMapping("/getPersonagem/{id}")
    public Optional<Personagem> getPersonagem(@PathVariable(value = "id") int idPersonagem){
        return personagemService.getPersonagemPorId(idPersonagem);
    }
    @PostMapping("/salvarPersonagem")
    public void salvarPersonagem(@RequestBody Personagem personagem){
        this.personagemService.salvarPersonagem(personagem);
    }
    @GetMapping("/getPersonagens")
    public List<Personagem> getPersonagens(){
        return personagemService.getPersonagens();
    }

    @PutMapping("/atualizarPersonagem/{id}")
    public void atualizarPersonagem(@PathVariable(value = "id") int idPersonagem,@RequestBody Personagem novasCaracteristicas) {
        personagemService.atualizarPersonagem(idPersonagem, novasCaracteristicas);
    }
    @DeleteMapping("/deletarPersonagem/{id}")
    public void deletarPersonagem(@PathVariable(value = "id") int idPersonagem){
        personagemService.deletarPersonagem(idPersonagem);
    }
}
