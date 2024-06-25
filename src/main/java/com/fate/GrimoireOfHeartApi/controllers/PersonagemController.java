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
    @Autowired
    private PersonagemService personagemService;
    @Autowired
    private PersonagemMapper mapper;
    @PostMapping()
    public ResponseEntity<PersonagemResponse> salvarPersonagem(@RequestBody PersonagemRequest personagemRequest){

        Personagem personagem = mapper.toPersonagem(personagemRequest);

        Personagem personagemSalvo = personagemService.salvarPersonagem(personagem);

        PersonagemResponse personagemResponse = mapper.toPersonagemResponse(personagemSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(personagemResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonagemResponse> getPersonagem(@PathVariable(value = "id") int idPersonagem) throws Exception {
        Optional<Personagem> personagemRetornado = personagemService.getPersonagemPorId(idPersonagem);

        PersonagemResponse personagemResponse = mapper.toPersonagemResponse(personagemRetornado.orElseThrow());

        return ResponseEntity.status(HttpStatus.OK).body(personagemResponse);
    }
    @GetMapping("/personagens")
    public ResponseEntity<List<PersonagemResponse>> getPersonagens(){
        List<Personagem> personagens = personagemService.getPersonagens();

        List<PersonagemResponse> personagensResponse = mapper.toPersonagemListResponse(personagens);
        return ResponseEntity.status(HttpStatus.OK).body(personagensResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonagemResponse> atualizarPersonagem(@RequestBody PersonagemRequest personagemRequest, @PathVariable(value = "id") long personagemId) throws Exception {
        Personagem personagem = mapper.toPersonagem(personagemRequest);

        Personagem personagemAtualizado = personagemService.atualizarPersonagem(personagem, personagemId);

        PersonagemResponse personagemResponse = mapper.toPersonagemResponse(personagemAtualizado);

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
