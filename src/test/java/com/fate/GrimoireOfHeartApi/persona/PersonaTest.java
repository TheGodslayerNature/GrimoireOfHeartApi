package com.fate.GrimoireOfHeartApi.persona;

import com.fate.GrimoireOfHeartApi.model.persona.Persona;
import com.fate.GrimoireOfHeartApi.model.tiposdemagia.TiposDeMagia;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PersonaTest {
    @Test
    void deveConseguirAdicionarTiposDeMagia() {
        ArrayList<TiposDeMagia> tipoDeMagias = new ArrayList<>();
        tipoDeMagias.add(TiposDeMagia.Fogo);
        Persona persona = new Persona("Ariane",tipoDeMagias);

        String descricaoEsperada = "Um destrutivo Tipo de dano focado em intenso dano em área, ao custo de \n" +
                "menor dano contra alvos individuais.";
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
    void personaDeveTerInteracoesComOsTiposDeMagia() {
        ArrayList<TiposDeMagia> tipoDeMagias = new ArrayList<>();
        tipoDeMagias.add(TiposDeMagia.Fogo);
        Persona persona = new Persona("Ariane",tipoDeMagias);
        persona.addResistencia(TiposDeMagia.Luz);

        assertThat(persona.interacoesDeMagias()).isEqual("Null");

        tipoDeMagias.add(TiposDeMagia.Luz);
        persona = new Persona("Ariane",tipoDeMagias);
        assertThat(persona.interacoesDeMagias()).isEqual("Luz: resiste");
    }
}
