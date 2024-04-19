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
        AtributosDeBatalha atributosDeBatalha = new AtributosDeBatalha();
        Personagem manuel = new Personagem("Manuel", atributosDeBatalha);

        String esperado = "FOR: 0, TEC: 0, VIT: 0, MAG: 0, AGI: 0, SOR: 0";
        assertThat(manuel.printAtributosDeBatalha()).isEqualTo(esperado);
    }

    @Test
    void personagemDeveConseguirAdicionarPontosADeterminadoAtributoSeTiverPontosSuficiente() throws Exception{
        AtributosDeBatalha atributosDeBatalha = new AtributosDeBatalha();
        Personagem manuel = new Personagem("Manuel", atributosDeBatalha);
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
        AtributosSociais atributosSociais = new AtributosSociais();
        Personagem jaden = new Personagem("Jaden", new AtributosDeBatalha(), atributosSociais);
        jaden.setPontosSociais(6L);

        jaden.addPontoACharme(5L);
        String esperado = "CONHECIMEHTOS: 0, DISCIPLINA: 0, EMPATIA: 0, CHARME: 5, EXPRESSÃO: 0, CORAGEM: 0";
        assertThat(jaden.printAtributosSociais()).isEqualTo(esperado);
    }
}
