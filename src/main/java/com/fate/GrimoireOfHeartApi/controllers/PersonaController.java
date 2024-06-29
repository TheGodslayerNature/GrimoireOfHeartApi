package com.fate.GrimoireOfHeartApi.controllers;
import com.fate.GrimoireOfHeartApi.dto.request.PersonaRequest;
import com.fate.GrimoireOfHeartApi.dto.response.PersonaResponse;
import com.fate.GrimoireOfHeartApi.mapper.PersonaMapper;
import com.fate.GrimoireOfHeartApi.model.persona.Persona;
import com.fate.GrimoireOfHeartApi.services.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    PersonaService personaService;
    @Autowired
    PersonaMapper mapper;
    @PostMapping
    public ResponseEntity<PersonaResponse> salvarPersona(@RequestBody @Valid PersonaRequest request){
        Persona persona = mapper.toPersona(request);

        Persona personaSalvo = personaService.salvarPersona(persona);

        PersonaResponse response = mapper.toPersonaResponse(personaSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponse> getPersona(@PathVariable(value = "id") Long personaId){
        Persona persona = personaService.getPersona(personaId);

        PersonaResponse response = mapper.toPersonaResponse(persona);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponse> atualizarPersonagem(@RequestBody PersonaRequest request, @PathVariable(value = "id") long personaId) throws Exception {
        Persona persona = mapper.toPersona(request);

        Persona personaAtualizado = personaService.atualizarPersona(persona, personaId);

        PersonaResponse response = mapper.toPersonaResponse(personaAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/personas")
    public ResponseEntity<List<PersonaResponse>> personas(){
        List<Persona> personas = personaService.listarPersonas();

        List<PersonaResponse> listResponse = mapper.toPersonaListResponse(personas);

        return ResponseEntity.status(HttpStatus.OK).body(listResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPersonagem(@PathVariable(value = "id") long idPersona) throws Exception {
        personaService.deletarPersona(idPersona);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
