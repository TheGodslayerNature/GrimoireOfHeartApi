package com.fate.GrimoireOfHeartApi.mapper;

import com.fate.GrimoireOfHeartApi.dto.request.PersonagemRequest;
import com.fate.GrimoireOfHeartApi.dto.response.PersonagemResponse;
import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PersonagemMapper {

    @Autowired
    private final ModelMapper modelMapper;

    public Personagem toPersonagem(PersonagemRequest request) {
        Personagem personagem = modelMapper.map(request, Personagem.class);
        return personagem;
    }

    public PersonagemResponse toPersonagemResponse(Personagem personagem){
        return modelMapper.map(personagem, PersonagemResponse.class);
    }
    public List<PersonagemResponse> toPersonagemListResponse(List<Personagem> personagens){
        return personagens.stream().map(this::toPersonagemResponse).collect(Collectors.toList());
    }
}
