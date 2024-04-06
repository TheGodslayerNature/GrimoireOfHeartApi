package com.fate.GrimoireOfHeartApi.personagem;

import com.fate.GrimoireOfHeartApi.model.atributo.Atributos;
import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonagemTest {
    @Test
    void deveConseguirAcessarAtributosDeBatalha() {
        Atributos atributos = new Atributos();
        Personagem manuel = new Personagem("James", "Manuel", atributos);

        List<String> atributosEsperados = Arrays.asList("FOR: 0", "TEC: 0", "VIT: 0", "MAG: 0", "AGI: 0", "SOR: 0");
        assertThat(manuel.getAtributosDeBatalha().nomesDosAtributos()).isEqualTo(atributosEsperados);
    }

    @Test
    void deveSerPosssivelModificarOsValoresDosAtributos() {
        Atributos atributos = new Atributos();
        Personagem manuel = new Personagem("James", "Manuel", atributos);

        List<String> atributosEsperados = Arrays.asList("FOR: 0", "TEC: 0", "VIT: 0", "MAG: 0", "AGI: 0", "SOR: 0");
        assertThat(manuel.getAtributosDeBatalha().nomesDosAtributos()).isEqualTo(atributosEsperados);

        manuel.adicionarPontos("FOR", 2);

        atributosEsperados = Arrays.asList("FOR: 2", "TEC: 0", "VIT: 0", "MAG: 0", "AGI: 0", "SOR: 0");
        assertThat(manuel.getAtributosDeBatalha().nomesDosAtributos()).isEqualTo(atributosEsperados);

        manuel.adicionarPontos("AGI", 3);
        atributosEsperados = Arrays.asList("FOR: 2", "TEC: 0", "VIT: 0", "MAG: 0", "AGI: 3", "SOR: 0");
        assertThat(manuel.getAtributosDeBatalha().nomesDosAtributos()).isEqualTo(atributosEsperados);
    }
}
