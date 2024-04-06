package com.fate.GrimoireOfHeartApi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fate.GrimoireOfHeartApi.model.atributo.Atributos;
import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    private Personagem personagemManuel;
    private Personagem personagemManuela;
    private Personagem personagemJames;
    private Atributos atributos;
    private String transformarParaJson(Personagem personagem) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(personagem);
    }

    @BeforeEach
    void setUp() {
        atributos = new Atributos();
        personagemManuel = new Personagem("Manuel", "Alessandro", null);
        personagemManuela = new Personagem("Manuela", "Alessandra", null);
        personagemJames = new Personagem("James", "Navas", null);
    }

    @AfterEach
    void tearDown() {
        personagemController.deletarPersonagem(personagemManuel.getId());
        personagemController.deletarPersonagem(personagemManuela.getId());
        personagemController.deletarPersonagem(personagemJames.getId());
    }

    @Test
    void deveConseguirTerSucessoAoRealizarUmPost() throws Exception {
        personagemController.salvarPersonagem(personagemManuel);

        MockHttpServletResponse resultado = mockMvc.perform(post("/personagem/salvarPersonagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transformarParaJson(personagemManuel)))
                .andReturn().getResponse();

        assertThat(HttpStatus.OK.value()).isEqualTo(resultado.getStatus());
    }

    @Test
    void deveRetornarUmPersonagemJsonAoFazerSuaSolicitacao() throws Exception {
        personagemController.salvarPersonagem(personagemManuel);

        mockMvc.perform(post("/personagem/salvarPersonagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transformarParaJson(personagemManuel)));

        MockHttpServletResponse resultado = mockMvc.perform(get("/personagem/getPersonagem/1"))
                .andReturn().getResponse();

        String personagemJsonExpected = "{\"nomeJogador\":\"Manuel\",\"nomePersonagem\":\"Alessandro\",\"atributosDeBatalha\":null,\"id\":1}";

                assertThat(HttpStatus.OK.value()).isEqualTo(resultado.getStatus());
                assertThat(resultado.getContentAsString()).isEqualTo(personagemJsonExpected);
    }

    @Test
    void deveRetornarTodosOsPersonagens() throws Exception {
        personagemController.salvarPersonagem(personagemManuel);

//        mockMvc.perform(post("/personagem/salvarPersonagem")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(transformarParaJson(personagem)));
//
//        //Acentuações estão bugando
//
        personagemController.salvarPersonagem(personagemJames);
//
//        mockMvc.perform(post("/personagem/salvarPersonagem")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(transformarParaJson(personagem)));
//
        MockHttpServletResponse resultado = mockMvc.perform(get("/personagem/getPersonagens"))
                .andReturn().getResponse();

        System.out.println(personagemController.getPersonagens());

        String attString = ",\"atributosDeBatalha\":null,";

        String personagensExpected = "[{\"nomeJogador\":\"Manuel\",\"nomePersonagem\":\"Alessandro\",\"atributosDeBatalha\":null,\"id\":1},{\"nomeJogador\":\"James\",\"nomePersonagem\":\"Navas\",\"atributosDeBatalha\":null,\"id\":2}]";

        System.out.println(personagemController.getPersonagens());
        assertThat(resultado.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(resultado.getContentAsString()).isEqualTo(personagensExpected);
    }

    @Test
    void deveTerSucessoAoAtualizarDadosDeUmPersonagem() throws Exception{
        personagemController.salvarPersonagem(personagemManuel);

        mockMvc.perform(post("/personagem/salvarPersonagem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformarParaJson(personagemManuel)));

        personagemController.atualizarPersonagem(personagemManuel.getId(), personagemManuela);

        mockMvc.perform(put("/personagem/atualizarPersonagem/1")
                .contentType(MediaType.APPLICATION_JSON).content(transformarParaJson(personagemManuela)));

        MockHttpServletResponse resultado = mockMvc.perform(get("/personagem/getPersonagem/1"))
                .andReturn().getResponse();

        String personagemJsonExpected = "{\"nomeJogador\":\"Manuela\",\"nomePersonagem\":\"Alessandra\",\"atributosDeBatalha\":null,\"id\":1}";
        assertThat(resultado.getContentAsString()).isEqualTo(personagemJsonExpected);
    }

    @Test
    void deveTerSucessoAoDeletarUmPersonagem() throws Exception{
        personagemController.salvarPersonagem(personagemManuela);

        personagemController.deletarPersonagem(personagemManuela.getId());

        mockMvc.perform(delete("/personagem/deletarPersonagem/1")
                .contentType(MediaType.APPLICATION_JSON).contentType(transformarParaJson(personagemManuela)));

        MockHttpServletResponse resultado = mockMvc.perform(get("/personagem/getPersonagem/1"))
                .andReturn().getResponse();

        System.out.println(personagemController.getPersonagens());
        //Arrumar um jeito de não testar com null
        assertThat(resultado.getContentAsString()).isEqualTo("null");
    }
}
