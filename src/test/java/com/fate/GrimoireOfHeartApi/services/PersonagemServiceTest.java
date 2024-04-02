package com.fate.GrimoireOfHeartApi.services;

import com.fate.GrimoireOfHeartApi.model.Personagem.Personagem;
import com.fate.GrimoireOfHeartApi.repositories.PersonagemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonagemServiceTest {
    @Mock
    private PersonagemRepository personagemRepository;
    @InjectMocks
    private PersonagemService personagemService;
    @Test
    void deveSalvarPersonagem() {
        Personagem personagem = new Personagem("João", "Madelin",1);

        personagemService.salvarPersonagem(personagem);

        verify(personagemRepository).saveAndFlush(new Personagem("João", "Madelin",1));

        when(personagemRepository.findById(1)).thenReturn(Optional.of(personagem));

        Optional<Personagem> esperado = personagemService.getPersonagemPorId(1);

        assertThat(new Personagem("João", "Madelin",1)).isEqualTo(esperado.orElseThrow());
    }

    @Test
    void deveRetornarTodosOsPersonagens() {
        Personagem personagem = new Personagem("João", "Madelin",1);
        Personagem personagem2 = new Personagem("Maria", "Jaime",2);

        personagemService.salvarPersonagem(personagem);

        verify(personagemRepository).saveAndFlush(new Personagem("João", "Madelin",1));

        personagemService.salvarPersonagem(personagem2);

        verify(personagemRepository).saveAndFlush(new Personagem("Maria", "Jaime",2));

        List<Personagem> personagens = Arrays.asList(personagem,personagem2);
        when(personagemRepository.findAll()).thenReturn(personagens);

        assertThat(personagemRepository.findAll()).isEqualTo(personagemService.getPersonagens());
    }
}
