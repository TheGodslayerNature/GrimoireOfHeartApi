package com.fate.GrimoireOfHeartApi.controllers;

import com.fate.GrimoireOfHeartApi.dto.request.PersonagemRequest;
import com.fate.GrimoireOfHeartApi.dto.response.PersonagemResponse;
import com.fate.GrimoireOfHeartApi.mapper.PersonagemMapper;
import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
import com.fate.GrimoireOfHeartApi.services.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ResponseBody
@RequestMapping("/personagem")
public class PersonagemController {
    //Arrumar Service para suportar a transferencia do DTO
    //Criar requests e response de personagens
    // Criar mapper para tratar as response e requests
    @Autowired
    private PersonagemService personagemService;
    @PostMapping()
    public ResponseEntity<PersonagemResponse> salvarPersonagem(@RequestBody PersonagemRequest personagemRequest){

        Personagem personagem = PersonagemMapper.toPersonagem(personagemRequest);

        Personagem personagemSalvo = personagemService.salvarPersonagem(personagem);

        PersonagemResponse personagemResponse = PersonagemMapper.toPersonagemResponse(personagemSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(personagemResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonagemResponse> getPersonagem(@PathVariable(value = "id") int idPersonagem) throws Exception {
        Optional<Personagem> personagemRetornado = personagemService.getPersonagemPorId(idPersonagem);

        PersonagemResponse personagemResponse = PersonagemMapper.toPersonagemResponse(personagemRetornado.orElseThrow());

        return ResponseEntity.status(HttpStatus.OK).body(personagemResponse);
    }
    @GetMapping("/personagens")
    public ResponseEntity<List<PersonagemResponse>> getPersonagens(){
        List<Personagem> personagens = personagemService.getPersonagens();

        List<PersonagemResponse> personagensResponse = PersonagemMapper.toPersonagemListResponse(personagens);
        return ResponseEntity.status(HttpStatus.OK).body(personagensResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonagemResponse> atualizarPersonagem(@RequestBody PersonagemRequest personagemRequest, @PathVariable(value = "id") long personagemId) throws Exception {
        Personagem personagem = PersonagemMapper.toPersonagem(personagemRequest);

        Personagem personagemAtualizado = personagemService.atualizarPersonagem(personagem, personagemId);

        PersonagemResponse personagemResponse = PersonagemMapper.toPersonagemResponse(personagemAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(personagemResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPersonagem(@PathVariable(value = "id") long idPersonagem) throws Exception {
        personagemService.deletarPersonagem(idPersonagem);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<Void> deletarTodos() {
        personagemService.deletarTodos();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
