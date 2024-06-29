package com.fate.GrimoireOfHeartApi.mapper;

import com.fate.GrimoireOfHeartApi.dto.request.PersonaRequest;
import com.fate.GrimoireOfHeartApi.dto.response.PersonaResponse;
import com.fate.GrimoireOfHeartApi.model.persona.Persona;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PersonaMapper {
    @Autowired
    private ModelMapper mapper;

    public Persona toPersona(PersonaRequest request){
        Persona persona = mapper.map(request, Persona.class);
        return persona;
    }

    public PersonaResponse toPersonaResponse(Persona persona){
        PersonaResponse response = mapper.map(persona, PersonaResponse.class);
        return response;
    }

    public List<PersonaResponse> toPersonaListResponse(List<Persona> personas){
        return personas.stream().map(this::toPersonaResponse).collect(Collectors.toList());
    }
}
