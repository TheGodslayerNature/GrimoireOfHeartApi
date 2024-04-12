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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonagemServiceTest {
    @Mock
    private PersonagemRepository personagemRepository;
    @InjectMocks
    private PersonagemService personagemService;
    @Test
    void deveSalvarPersonagem() throws Exception {
        Personagem personagem = new Personagem("Madelin",new AtributosDeBatalha());

        personagemService.salvarPersonagem(personagem);

        verify(personagemRepository).save(new Personagem("Madelin",new AtributosDeBatalha()));

        when(personagemRepository.findById(1)).thenReturn(Optional.of(personagem));

        Optional<Personagem> esperado = personagemService.getPersonagemPorId(1);

        assertThat(new Personagem( "Madelin",new AtributosDeBatalha())).isEqualTo(esperado.orElseThrow());
    }

    @Test
    void deveRetornarTodosOsPersonagens() {
        Personagem personagem = new Personagem( "Madelin",new AtributosDeBatalha());
        Personagem personagem2 = new Personagem( "Jaime",new AtributosDeBatalha());

        personagemService.salvarPersonagem(personagem);

        verify(personagemRepository).save(new Personagem("Madelin",new AtributosDeBatalha()));

        personagemService.salvarPersonagem(personagem2);

        verify(personagemRepository).save(new Personagem("Jaime",new AtributosDeBatalha()));

        List<Personagem> personagens = Arrays.asList(personagem,personagem2);
        when(personagemRepository.findAll()).thenReturn(personagens);

        assertThat(personagemRepository.findAll()).isEqualTo(personagemService.getPersonagens());
    }
}
