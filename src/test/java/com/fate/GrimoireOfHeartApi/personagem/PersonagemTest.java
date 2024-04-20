package com.fate.GrimoireOfHeartApi.personagem;

import com.fate.GrimoireOfHeartApi.exceptions.NaoExistemPontosSuficientes;
import com.fate.GrimoireOfHeartApi.model.atributo.AtributosDeBatalha;
import com.fate.GrimoireOfHeartApi.model.atributo.AtributosSociais;
import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PersonagemTest {
    @Test
    void deveConseguirAcessarAtributosDeBatalha(){
        Personagem manuel = new Personagem("Manuel");

        String esperado = "FOR: 0, TEC: 0, VIT: 0, MAG: 0, AGI: 0, SOR: 0";
        assertThat(manuel.printAtributosDeBatalha()).isEqualTo(esperado);
    }

    @Test
    void personagemDeveConseguirAdicionarPontosADeterminadoAtributoSeTiverPontosSuficiente() throws Exception{
        Personagem manuel = new Personagem("Manuel");
        manuel.setPontosDeBatalha(3L);

        String esperado = "FOR: 0, TEC: 0, VIT: 0, MAG: 0, AGI: 0, SOR: 0";
        assertThat(manuel.printAtributosDeBatalha()).isEqualTo(esperado);

        manuel.addPontoAFor(2L);

        esperado = "FOR: 2, TEC: 0, VIT: 0, MAG: 0, AGI: 0, SOR: 0";
        assertThat(manuel.printAtributosDeBatalha()).isEqualTo(esperado);

        assertThatThrownBy( () ->  manuel.addPontoAAgi(3L))
                .isInstanceOf(NaoExistemPontosSuficientes.class)
                .hasMessage("Não existem pontos suficientes");

        manuel.addPontoAAgi(1L);
        esperado = "FOR: 2, TEC: 0, VIT: 0, MAG: 0, AGI: 1, SOR: 0";
        assertThat(manuel.printAtributosDeBatalha()).isEqualTo(esperado);
    }

    @Test
    void oPersonagemDeveConseguirAcessarEModificarAtributosSociais() throws NaoExistemPontosSuficientes {
        Personagem jaden = new Personagem("Jaden");
        jaden.setPontosSociais(6L);

        jaden.addPontoACharme(5L);
        jaden.addPontoADisciplina(1L);
        String esperado = "CONHECIMENTO: 0, DISCIPLINA: 1, EMPATIA: 0, CHARME: 5, EXPRESSÃO: 0, CORAGEM: 0";
        assertThat(jaden.printAtributosSociais()).isEqualTo(esperado);

        assertThatThrownBy( () -> jaden.addPontoAExpressao(1L))
                .isInstanceOf(NaoExistemPontosSuficientes.class)
                .hasMessage("Não existem pontos suficientes");

        jaden.setPontosSociais(2L);
        jaden.addPontoACoragem(1L);

        esperado = "CONHECIMENTO: 0, DISCIPLINA: 1, EMPATIA: 0, CHARME: 5, EXPRESSÃO: 0, CORAGEM: 1";
        assertThat(jaden.printAtributosSociais()).isEqualTo(esperado);
        assertThat(jaden.getPontosSociais()).isEqualTo(1L);
    }
}
