package com.fate.GrimoireOfHeartApi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fate.GrimoireOfHeartApi.dto.request.PersonagemRequest;
import com.fate.GrimoireOfHeartApi.model.persona.Persona;
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

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonagemControllerTest {
    //Mudando alguns caminhos da chamada http, por favor se lembre de olhar
    @Autowired
    private PersonagemController personagemController;
    @Autowired
    private MockMvc mockMvc;

    private String transformarParaJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        personagemController.deletarTodos();
    }

    @Test
    void deveConseguirTerSucessoAoRealizarUmPost() throws Exception {
        PersonagemRequest request = new PersonagemRequest("Ariel");
        personagemController.salvarPersonagem(request);

        MockHttpServletResponse resultado = mockMvc.perform(post("/personagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transformarParaJson(request)))
                .andReturn().getResponse();

        String[] parts = resultado.getContentAsString().split("[{}]");

        assertThat(HttpStatus.CREATED.value()).isEqualTo(resultado.getStatus());
        assertThat(parts[1]).isEqualTo("\"nomePersonagem\":\"Ariel\"");
    }

    @Test
    void deveRetornarUmPersonagemJsonAoFazerSuaSolicitacao() throws Exception {
        PersonagemRequest request = new PersonagemRequest("Ariel");
        personagemController.salvarPersonagem(request);

        mockMvc.perform(post("/personagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transformarParaJson(request)));

        MockHttpServletResponse resultado = mockMvc.perform(get("/personagem/1"))
                .andReturn().getResponse();

        String[] parts = resultado.getContentAsString().split("[{}]");

        assertThat(HttpStatus.OK.value()).isEqualTo(resultado.getStatus());
        assertThat(parts[1]).isEqualTo("\"nomePersonagem\":\"Ariel\"");
//                assertThat(personagemController.getPersonagem(personagemManuel.getId()).orElseThrow()).isEqualTo(personagemManuel);
    }
    @Test
    void deveTerSucessoAoAtualizarDadosDeUmPersonagem() throws Exception{
        PersonagemRequest request = new PersonagemRequest("Madone");
        personagemController.salvarPersonagem(request);

        MockHttpServletResponse resultado = mockMvc.perform(post("/personagem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformarParaJson(request))).andReturn().getResponse();

        request = new PersonagemRequest("Oliver");

        assertThat(HttpStatus.CREATED.value()).isEqualTo(resultado.getStatus());
        assertThat(resultado.getContentAsString().contains("Madone")).isEqualTo(true);

        personagemController.atualizarPersonagem(request, 1L);

        resultado = mockMvc.perform(put("/personagem/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformarParaJson(request)))
                .andReturn()
                .getResponse();

        String[] parts = resultado.getContentAsString().split("[{}]");

        assertThat(HttpStatus.OK.value()).isEqualTo(resultado.getStatus());
        assertThat(parts[1]).isEqualTo("\"nomePersonagem\":\"Oliver\"");
    }

//    @Test
//    void deveTerSucessoAoDeletarUmPersonagem() throws Exception{
//        personagemController.salvarPersonagem(personagemManuela);
//
//        personagemController.deletarPersonagem(personagemManuela.getId());
//
//        mockMvc.perform(delete("/personagem/deletarPersonagem/1")
//                .contentType(MediaType.APPLICATION_JSON).contentType(transformarParaJson(personagemManuela)));
//
//
//        MockHttpServletResponse resultado = mockMvc.perform(get("/personagem/getPersonagem/1"))
//                .andReturn().getResponse();
//
//        System.out.println(personagemController.getPersonagens());
//        //Arrumar um jeito de não testar com null
//        assertThat(resultado.getContentAsString()).isEqualTo("null");
//    }
}
