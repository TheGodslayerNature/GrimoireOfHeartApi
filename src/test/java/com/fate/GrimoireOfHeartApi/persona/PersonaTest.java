package com.fate.GrimoireOfHeartApi.persona;

import com.fate.GrimoireOfHeartApi.model.Magia.Magia;
import com.fate.GrimoireOfHeartApi.model.persona.Persona;
import com.fate.GrimoireOfHeartApi.model.tiposdemagia.TiposDeMagia;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        assertThat(persona.getInteracoesDeMagias().get("Luz")).isEqualTo("Neutro");

        tipoDeMagias.add(TiposDeMagia.Luz);
        persona = new Persona("Ariane",tipoDeMagias);
        persona.addResistencia(TiposDeMagia.Luz);
        assertThat(persona.getInteracoesDeMagias().get("Luz")).isEqualTo("Resiste");
    }

    @Test
    void personaDeveConseguirAdiconarFraquezas() {
        ArrayList<TiposDeMagia> tipoDeMagias = new ArrayList<>();
        tipoDeMagias.add(TiposDeMagia.Fogo);
        tipoDeMagias.add(TiposDeMagia.Luz);
        Persona persona = new Persona("Ariane",tipoDeMagias);
        persona.addFraqueza(TiposDeMagia.Trevas);

        assertThat(persona.getInteracoesDeMagias().get("Trevas")).isEqualTo("Fraco");

        persona.addFraqueza(TiposDeMagia.Luz);
        assertThat(persona.getInteracoesDeMagias().get("Luz")).isEqualTo("Neutro");
    }

    @Test
    void personaDeveConseguirAdicionarUmaMagiaNoSeuDeck() throws IOException, JSONException, ParseException {
        ArrayList<TiposDeMagia> tipoDeMagias = new ArrayList<>();
        tipoDeMagias.add(TiposDeMagia.Fogo);
        tipoDeMagias.add(TiposDeMagia.Luz);
        Persona persona = new Persona("Ariane",tipoDeMagias);

        String content = new String(Files.readAllBytes(Paths.get("src/main/java/com/fate/GrimoireOfHeartApi/model/Magia/Magia.json")));
        JSONObject jsonObject = new JSONObject(content);

        JSONObject obj = jsonObject.getJSONObject("Físico");
        JSONArray magias = obj.getJSONArray("Tier 1");
        JSONObject magiaEscolhida = magias.getJSONObject(0);
        Magia magia = new Magia(1,magiaEscolhida.get("Nome").toString(),magiaEscolhida.get("Categorias").toString(),magiaEscolhida.get("Tempo").toString(),
                magiaEscolhida.get("Alcance").toString(),magiaEscolhida.get("Duracao").toString(),magiaEscolhida.get("Efeito").toString(),magiaEscolhida.get("Descricao").toString());

        persona.addMagia(magia);
        assertThat(magia.getNome()).isEqualTo(persona.getDeck()[0].getNome());
    }
}
