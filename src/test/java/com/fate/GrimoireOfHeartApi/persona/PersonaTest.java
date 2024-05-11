package com.fate.GrimoireOfHeartApi.persona;

import com.fate.GrimoireOfHeartApi.model.persona.Persona;
import com.fate.GrimoireOfHeartApi.model.tiposdemagia.TiposDeMagia;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonaTest {
    @Test
    void deveConseguirAdicionarTiposDeMagia() {
        ArrayList<TiposDeMagia> tipoDeMagias = new ArrayList<>();
        tipoDeMagias.add(TiposDeMagia.Fogo);
        Persona persona = new Persona("Ariane",tipoDeMagias);

        String descricaoEsperada = "Um destrutivo Tipo de dano focado em intenso dano em área, ao custo de " + "menor dano contra alvos individuais.";
        assertThat(persona.getMeusTiposDeMagia().get(0).getNome()).isEqualTo("Fogo");
        assertThat(persona.getMeusTiposDeMagia().get(0).getCategoria()).isEqualTo("Dano");
        assertThat(persona.getMeusTiposDeMagia().get(0).getDescricao()).isEqualTo(descricaoEsperada);

        tipoDeMagias.add(TiposDeMagia.Luz);
        persona = new Persona("Ariane",tipoDeMagias);
        descricaoEsperada = "Magias Básicas de Luz causam dano alto contra alvos com PV alto, enquanto " +
                "as magias de Zênite oferecem efeitos em área e utilidade extra.";
        assertThat(persona.getMeusTiposDeMagia().get(1).getNome()).isEqualTo("Luz");
        assertThat(persona.getMeusTiposDeMagia().get(1).getCategoria()).isEqualTo("Dano");
        assertThat(persona.getMeusTiposDeMagia().get(1).getDescricao()).isEqualTo(descricaoEsperada);
    }

    @Test
    void personaDeveTerResistenciaATiposDeMagia() {
        ArrayList<TiposDeMagia> tipoDeMagias = new ArrayList<>();
        tipoDeMagias.add(TiposDeMagia.Fogo);
        Persona persona = new Persona("Ariane",tipoDeMagias);
        persona.addResistencia(TiposDeMagia.Luz);

        assertThat(persona.getInteracoesDeMagias().get(0)).isEqualTo("Você não possui o tipo da magia");

        tipoDeMagias.add(TiposDeMagia.Luz);
        persona = new Persona("Ariane",tipoDeMagias);
        persona.addResistencia(TiposDeMagia.Luz);
        assertThat(persona.getInteracoesDeMagias().get(0)).isEqualTo("Luz: Resiste");
    }

    @Test
    void personaDeveConseguirAdiconarFraquezas() {
        ArrayList<TiposDeMagia> tipoDeMagias = new ArrayList<>();
        tipoDeMagias.add(TiposDeMagia.Fogo);
        tipoDeMagias.add(TiposDeMagia.Luz);
        Persona persona = new Persona("Ariane",tipoDeMagias);
        persona.addFraqueza(TiposDeMagia.Trevas);

        assertThat(persona.getInteracoesDeMagias().get(0)).isEqualTo("Trevas: Fraco");

        persona.addFraqueza(TiposDeMagia.Luz);
        assertThat(persona.getInteracoesDeMagias().get(1)).isEqualTo("Você não pode ser fraco a um tipo seu");
    }
}
