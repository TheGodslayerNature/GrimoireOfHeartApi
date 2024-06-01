package com.fate.GrimoireOfHeartApi.personagem;

import com.fate.GrimoireOfHeartApi.exceptions.NaoExistemPontosSuficientes;
import com.fate.GrimoireOfHeartApi.model.Klass.Coringa;
import com.fate.GrimoireOfHeartApi.model.Klass.Klass;
import com.fate.GrimoireOfHeartApi.model.persona.Persona;
import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PersonagemTest {
    @Test()
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
    void personagemDeveConseguirAcessarEModificarAtributosSociais() throws NaoExistemPontosSuficientes {
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

    @Test
    void personagemDeveTerVidaEEnergiaCalculasConformeSeusAtributos() throws NaoExistemPontosSuficientes {
        Personagem jaden = new Personagem("Jaden");

        assertThat(jaden.getVida()).isEqualTo(30);
        assertThat(jaden.getEnergia()).isEqualTo(0);

        jaden.setPontosDeBatalha(1L);
        jaden.addPontoAVit(1L);

        assertThat(jaden.getVida()).isEqualTo(31);
        assertThat(jaden.getEnergia()).isEqualTo(1);

        jaden.setPontosDeBatalha(1L);
        jaden.addPontoAVit(1L);

        assertThat(jaden.getVida()).isEqualTo(32);
        assertThat(jaden.getEnergia()).isEqualTo(2);

        jaden.setNivel(2);

        assertThat(jaden.getAtributosDeBatalha().getVitalidade()).isEqualTo(2L);
        assertThat(jaden.getNivel()).isEqualTo(2L);
        assertThat(jaden.getVida()).isEqualTo(39);
        assertThat(jaden.getEnergia()).isEqualTo(3);

        jaden.setNivel(3);

        assertThat(jaden.getAtributosDeBatalha().getVitalidade()).isEqualTo(2L);
        assertThat(jaden.getNivel()).isEqualTo(3L);
        assertThat(jaden.getVida()).isEqualTo(46);
        assertThat(jaden.getEnergia()).isEqualTo(3);

        jaden.setNivel(4);

        assertThat(jaden.getAtributosDeBatalha().getVitalidade()).isEqualTo(2L);
        assertThat(jaden.getNivel()).isEqualTo(4L);
        assertThat(jaden.getVida()).isEqualTo(53);
        assertThat(jaden.getEnergia()).isEqualTo(4);
    }
    @Test
    void criarPersonagemComSuaPersona() {
        Persona persona = new Persona("Ariane");
        Personagem jaden = new Personagem("Jaden", persona);

        assertThat(jaden.getPersona().getNome()).isEqualTo("Ariane");
    }

    @Test
    void criarPersonagemComClasse() {
        Coringa coringa = new Coringa("Coringa");
        Personagem personagem = new Personagem("Jaden", coringa);

        //Tentar conseguir pegar as habilidades
        assertThat(personagem.getKlass().getNomeDaClasse()).isEqualTo("Coringa");
        assertThat(personagem.getKlass().getHabilidades().get(0)).isEqualTo("Versatilidade");
    }
}
