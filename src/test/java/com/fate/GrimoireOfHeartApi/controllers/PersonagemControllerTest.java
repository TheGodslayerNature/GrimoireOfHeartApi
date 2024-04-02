package com.fate.GrimoireOfHeartApi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fate.GrimoireOfHeartApi.model.Personagem.Personagem;
import org.junit.jupiter.api.AfterEach;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonagemControllerTest {
    @Autowired
    private PersonagemController personagemController;
    @Autowired
    private MockMvc mockMvc;
    private String transformarParaJson(Personagem personagem) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(personagem);
    }

    @Test
    void deveConseguirTerSucessoAoRealizarUmPost() throws Exception {
        Personagem personagem = new Personagem("João", "Navas", 1);

        personagemController.salvarPersonagem(personagem);

        MockHttpServletResponse resultado = mockMvc.perform(post("/personagem/salvarPersonagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transformarParaJson(personagem)))
                .andReturn().getResponse();

        assertThat(HttpStatus.OK.value()).isEqualTo(resultado.getStatus());
    }

    @Test
    void deveRetornarUmPersonagemJsonAoFazerSuaSolicitacao() throws Exception {
        Personagem personagem = new Personagem("Manuel", "Alessandro", 2);

        personagemController.salvarPersonagem(personagem);

        mockMvc.perform(post("/personagem/salvarPersonagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transformarParaJson(personagem)));

        MockHttpServletResponse resultado = mockMvc.perform(get("/personagem/getPersonagem/2"))
                .andReturn().getResponse();

        String personagemJsonExpected = "{\"nomeJogador\":\"Manuel\",\"nomePersonagem\":\"Alessandro\",\"id\":2}";

                assertThat(HttpStatus.OK.value()).isEqualTo(resultado.getStatus());
                assertThat(personagemJsonExpected).isEqualTo(resultado.getContentAsString());
    }

    @Test
    void deveRetornarTodosOsPersonagens() throws Exception {
        Personagem personagem = new Personagem("Manuel", "Alessandro", 2);

        personagemController.salvarPersonagem(personagem);

        mockMvc.perform(post("/personagem/salvarPersonagem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformarParaJson(personagem)));

        //Acentuações estão bugando
        Personagem personagem2 = new Personagem("James", "Navas", 1);

        personagemController.salvarPersonagem(personagem2);

        mockMvc.perform(post("/personagem/salvarPersonagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transformarParaJson(personagem)));

        MockHttpServletResponse resultado = mockMvc.perform(get("/personagem/getPersonagens"))
                .andReturn().getResponse();

        String personagensExpected = "[{\"nomeJogador\":\"James\",\"nomePersonagem\":\"Navas\",\"id\":1},{\"nomeJogador\":\"Manuel\",\"nomePersonagem\":\"Alessandro\",\"id\":2}]";

        assertThat(resultado.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(personagensExpected).isEqualTo(resultado.getContentAsString());
    }

    @Test
    void deveTerSucessoAoAtualizarDadosDeUmPersonagem() throws Exception{
        Personagem personagem = new Personagem("Manuel", "Alessandro", 2);

        personagemController.salvarPersonagem(personagem);

        mockMvc.perform(post("/personagem/salvarPersonagem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformarParaJson(personagem)));

        Personagem personagemComNovasCaracteristicas = new Personagem("Manuela", "Alessandra", 2);

        personagemController.atualizarPersonagem(2, personagemComNovasCaracteristicas);

        mockMvc.perform(put("/personagem/atualizarPersonagem/2")
                .contentType(MediaType.APPLICATION_JSON).content(transformarParaJson(personagemComNovasCaracteristicas)));

        MockHttpServletResponse resultado = mockMvc.perform(get("/personagem/getPersonagens"))
                .andReturn().getResponse();

        String personagemJsonExpected = "{\"nomeJogador\":\"Manuela\",\"nomePersonagem\":\"Alessandra\",\"id\":2}";
        assertThat(resultado.getContentAsString()).isEqualTo(personagemJsonExpected);
    }

    @Test
    void deveTerSucessoAoDeletarUmPersonagem() throws Exception{
        Personagem personagem = new Personagem("Manuel", "Alessandro", 2);

        personagemController.salvarPersonagem(personagem);

        personagemController.deletarPersonagem(personagem.getId());

        mockMvc.perform(delete("/personagem/deletarPersonagem/2")
                .contentType(MediaType.APPLICATION_JSON).contentType(transformarParaJson(personagem)));

        MockHttpServletResponse resultado = mockMvc.perform(get("/personagem/getPersonagem/2"))
                .andReturn().getResponse();

        assertThat(resultado.getContentAsString()).isEqualTo("null");
    }
}
