package com.fate.GrimoireOfHeartApi.mapper;

import com.fate.GrimoireOfHeartApi.dto.request.PersonagemRequest;
import com.fate.GrimoireOfHeartApi.dto.response.PersonagemResponse;
import com.fate.GrimoireOfHeartApi.model.persona.Persona;
import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemMapper {
    public static Personagem toPersonagem(PersonagemRequest personagemRequest){
        Personagem personagem = new Personagem();
        personagem.setNomePersonagem(personagemRequest.nomePersonagem());

        return personagem;
    }

    public static PersonagemResponse toPersonagemResponse(Personagem personagem){
        PersonagemResponse personagemResponse = new PersonagemResponse(personagem.getNomePersonagem());

        return personagemResponse;
    }

    public static List<PersonagemResponse> toPersonagemListResponse(List<Personagem> personagens){
        List<PersonagemResponse> personagemResponses = new ArrayList<>();

        for (Personagem personagem:personagens) {
            PersonagemResponse personagemResponse = new PersonagemResponse(personagem.getNomePersonagem());
            personagemResponses.add(personagemResponse);
        }
        return personagemResponses;
    }
}
