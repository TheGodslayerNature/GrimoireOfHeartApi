package com.fate.GrimoireOfHeartApi.personagem;

import com.fate.GrimoireOfHeartApi.model.atributo.AtributosDeBatalha;
import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonagemTest {
    @Test
    void deveConseguirAcessarAtributosDeBatalha() {
        AtributosDeBatalha atributosDeBatalha = new AtributosDeBatalha();
        Personagem manuel = new Personagem("Manuel", atributosDeBatalha);

        String esperado = "FOR: 0, TEC: 0, VIT: 0, MAG: 0, AGI: 0, SOR: 0";
        assertThat(manuel.printAtributosDeBatalha()).isEqualTo(esperado);
    }

    @Test
    void personagemDeveConseguirAdicionarPontosADeterminadoAtributoSeTiverPontosSuficiente() {
        AtributosDeBatalha atributosDeBatalha = new AtributosDeBatalha();
        Personagem manuel = new Personagem("Manuel", atributosDeBatalha);
        manuel.setPontosDeBatalha(3L);

        String esperado = "FOR: 0, TEC: 0, VIT: 0, MAG: 0, AGI: 0, SOR: 0";
        assertThat(manuel.printAtributosDeBatalha()).isEqualTo(esperado);

        manuel.addPontoAFor(2L);

        esperado = "FOR: 2, TEC: 0, VIT: 0, MAG: 0, AGI: 0, SOR: 0";
        assertThat(manuel.printAtributosDeBatalha()).isEqualTo(esperado);

        manuel.addPontoAAgi(3L);
        esperado = "FOR: 2, TEC: 0, VIT: 0, MAG: 0, AGI: 3, SOR: 0";
        assertThat(manuel.printAtributosDeBatalha()).isEqualTo(esperado);
    }
//
//    @Test
//    void oPersonagemDeveConseguirAcessarEModificarAtributosSociais() {
//        AtributosSociais atributosSociais = new AtributosSociais();
//        Personagem jaden = new Personagem("Paulo", "Jaden", new AtributosDeBatalha(), atributosSociais);
//
//        List<String> atributosEsperados =  Arrays.asList("CHARME: 0", "CORAGEM: 0", "DISCIPLINA: 0", "EMPATIA: 0", "CONHECIMENTO: 0", "EXPRESSAO: 0");
//
//        assertThat(jaden.getAtributosSociais().nomeDosAtributosSociais()).containsAnyElementsOf(atributosEsperados);
//
//        jaden.adicionarPontoAAtributoSocial("EMPATIA", 2);
//
//        atributosEsperados =  Arrays.asList("CHARME: 0", "CORAGEM: 0", "DISCIPLINA: 0", "EMPATIA: 2", "CONHECIMENTO: 0", "EXPRESSAO: 0");
//        assertThat(jaden.getAtributosSociais().nomeDosAtributosSociais()).containsAll(atributosEsperados);
//    }
}
