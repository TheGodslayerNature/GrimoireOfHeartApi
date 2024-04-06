package com.fate.GrimoireOfHeartApi.services;

import com.fate.GrimoireOfHeartApi.model.atributo.Atributos;
import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
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
        Personagem personagem = new Personagem("João", "Madelin",new Atributos());

        personagemService.salvarPersonagem(personagem);

        verify(personagemRepository).save(new Personagem("João", "Madelin",new Atributos()));

        when(personagemRepository.findById(1)).thenReturn(Optional.of(personagem));

        Optional<Personagem> esperado = personagemService.getPersonagemPorId(1);

        assertThat(new Personagem("João", "Madelin",new Atributos())).isEqualTo(esperado.orElseThrow());
    }

    @Test
    void deveRetornarTodosOsPersonagens() {
        Personagem personagem = new Personagem("João", "Madelin",new Atributos());
        Personagem personagem2 = new Personagem("Maria", "Jaime",new Atributos());

        personagemService.salvarPersonagem(personagem);

        verify(personagemRepository).save(new Personagem("João", "Madelin",new Atributos()));

        personagemService.salvarPersonagem(personagem2);

        verify(personagemRepository).save(new Personagem("Maria", "Jaime",new Atributos()));

        List<Personagem> personagens = Arrays.asList(personagem,personagem2);
        when(personagemRepository.findAll()).thenReturn(personagens);

        assertThat(personagemRepository.findAll()).isEqualTo(personagemService.getPersonagens());
    }
}
