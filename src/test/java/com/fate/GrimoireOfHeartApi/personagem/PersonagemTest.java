package com.fate.GrimoireOfHeartApi.personagem;

import com.fate.GrimoireOfHeartApi.model.atributo.AtributosDeBatalha;
import com.fate.GrimoireOfHeartApi.model.atributo.AtributosSociais;
import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonagemTest {
    @Test
    void deveConseguirAcessarAtributosDeBatalha() {
        AtributosDeBatalha atributosDeBatalha = new AtributosDeBatalha();
        Personagem manuel = new Personagem("James", "Manuel", atributosDeBatalha);

        List<String> atributosEsperados = Arrays.asList("FOR: 0", "TEC: 0", "VIT: 0", "MAG: 0", "AGI: 0", "SOR: 0");
        assertThat(manuel.getAtributosDeBatalha().nomeDosAtributosDeBatalha()).isEqualTo(atributosEsperados);
    }

    @Test
    void deveSerPosssivelModificarOsValoresDosAtributos() {
        AtributosDeBatalha atributosDeBatalha = new AtributosDeBatalha();
        Personagem manuel = new Personagem("James", "Manuel", atributosDeBatalha);

        List<String> atributosEsperados = Arrays.asList("FOR: 0", "TEC: 0", "VIT: 0", "MAG: 0", "AGI: 0", "SOR: 0");
        assertThat(manuel.getAtributosDeBatalha().nomeDosAtributosDeBatalha()).isEqualTo(atributosEsperados);

        manuel.adicionarPontoAAtributoDeBatalha("FOR", 2);

        atributosEsperados = Arrays.asList("FOR: 2", "TEC: 0", "VIT: 0", "MAG: 0", "AGI: 0", "SOR: 0");
        assertThat(manuel.getAtributosDeBatalha().nomeDosAtributosDeBatalha()).isEqualTo(atributosEsperados);

        manuel.adicionarPontoAAtributoDeBatalha("AGI", 3);
        atributosEsperados = Arrays.asList("FOR: 2", "TEC: 0", "VIT: 0", "MAG: 0", "AGI: 3", "SOR: 0");
        assertThat(manuel.getAtributosDeBatalha().nomeDosAtributosDeBatalha()).isEqualTo(atributosEsperados);
    }

    @Test
    void oPersonagemDeveConseguirAcessarEModificarAtributosSociais() {
        AtributosSociais atributosSociais = new AtributosSociais();
        Personagem jaden = new Personagem("Paulo", "Jaden", new AtributosDeBatalha(), atributosSociais);

        List<String> atributosEsperados =  Arrays.asList("CHARME: 0", "CORAGEM: 0", "DISCIPLINA: 0", "EMPATIA: 0", "CONHECIMENTO: 0", "EXPRESSAO: 0");

        assertThat(jaden.getAtributosSociais().nomeDosAtributosSociais()).containsAnyElementsOf(atributosEsperados);

        jaden.adicionarPontoAAtributoSocial("EMPATIA", 2);

        atributosEsperados =  Arrays.asList("CHARME: 0", "CORAGEM: 0", "DISCIPLINA: 0", "EMPATIA: 2", "CONHECIMENTO: 0", "EXPRESSAO: 0");
        assertThat(jaden.getAtributosSociais().nomeDosAtributosSociais()).containsAll(atributosEsperados);
    }
}
