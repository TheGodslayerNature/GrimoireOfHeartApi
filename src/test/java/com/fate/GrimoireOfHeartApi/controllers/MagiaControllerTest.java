package com.fate.GrimoireOfHeartApi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fate.GrimoireOfHeartApi.model.Magia.Magia;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MagiaControllerTest {
    @Autowired
    private MagiaController magiaController;
    @Autowired
    private MockMvc mockMvc;
    public Magia construirMagia(String nomeDoTipoDeMagia, int localizacaoDaMagia) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("src/main/java/com/fate/GrimoireOfHeartApi/model/Magia/Magia.json")));
        JSONObject jsonObject = new JSONObject(content);

        JSONObject obj = jsonObject.getJSONObject(nomeDoTipoDeMagia);
        JSONArray magias = obj.getJSONArray("Tier 1");
        JSONObject magiaEscolhida = magias.getJSONObject(localizacaoDaMagia);

        Magia magia = new Magia(1,magiaEscolhida.get("Nome").toString(),magiaEscolhida.get("Categorias").toString(),magiaEscolhida.get("Tempo").toString(),
                magiaEscolhida.get("Alcance").toString(),magiaEscolhida.get("Duracao").toString(),magiaEscolhida.get("Efeito").toString(),magiaEscolhida.get("Descricao").toString());
        return magia;
    }
    private String transformarParaJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
    @Test
    void deveConseguirRealizarUmPostComSucesso() throws Exception{
        Magia magia = construirMagia("Fogo",0);
        magiaController.salvarMagia(magia);

        MockHttpServletResponse resultado = mockMvc.perform(post("/magia/salvarMagia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transformarParaJson(magia)))
                .andReturn().getResponse();

        assertThat(HttpStatus.OK.value()).isEqualTo(resultado.getStatus());
        assertThat(magiaController.getMagia(magia.getId()).orElseThrow()).isEqualTo(magia);
    }
}
