package com.fate.GrimoireOfHeartApi.services;

import com.fate.GrimoireOfHeartApi.model.atributo.AtributosDeBatalha;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonagemServiceTest {
    @Mock
    private PersonagemRepository personagemRepository;
    @InjectMocks
    private PersonagemService personagemService;
    @Test
    void deveSalvarPersonagem() throws Exception {
        Personagem personagem = new Personagem("Madelin");

        personagemService.salvarPersonagem(personagem);

        verify(personagemRepository).save(new Personagem("Madelin"));

        when(personagemRepository.findById(1L)).thenReturn(Optional.of(personagem));

        Optional<Personagem> esperado = personagemService.getPersonagemPorId(1);

        assertThat(new Personagem( "Madelin")).isEqualTo(esperado.orElseThrow());
    }

    @Test
    void deveRetornarTodosOsPersonagens() {
        Personagem personagem = new Personagem( "Madelin");
        Personagem personagem2 = new Personagem( "Jaime");

        personagemService.salvarPersonagem(personagem);
        personagemService.salvarPersonagem(personagem2);

        verify(personagemRepository, times(2)).save(any());

        List<Personagem> personagens = Arrays.asList(personagem,personagem2);
        when(personagemRepository.findAll()).thenReturn(personagens);

        assertThat(personagemRepository.findAll()).isEqualTo(personagemService.getPersonagens());
    }
}
