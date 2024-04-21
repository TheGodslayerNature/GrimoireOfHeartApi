package com.fate.GrimoireOfHeartApi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private Persona ariel;
    private Persona magno;
    private Persona cuchulain;

    private String transformarParaJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
    @BeforeEach
    void setUp() {
        ariel = new Persona("Ariel");
        magno = new Persona("Magno");
        cuchulain = new Persona("cuchulain");

        personagemManuel = new Personagem("Alessandro", ariel);
        personagemManuela = new Personagem("Manuela", magno);
        personagemJames = new Personagem("Navas", cuchulain);
    }

    @AfterEach
    void tearDown() {
        personagemController.deletarTodos();
    }

    @Test
    void deveConseguirTerSucessoAoRealizarUmPost() throws Exception {
        personagemController.salvarPersonagem(personagemManuel);

        MockHttpServletResponse resultado = mockMvc.perform(post("/personagem/salvarPersonagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transformarParaJson(personagemManuel)))
                .andReturn().getResponse();

        assertThat(HttpStatus.OK.value()).isEqualTo(resultado.getStatus());
        assertThat(personagemController.getPersonagem(personagemManuel.getId()).orElseThrow()).isEqualTo(personagemManuel);
    }

    @Test
    void deveRetornarUmPersonagemJsonAoFazerSuaSolicitacao() throws Exception {
        personagemController.salvarPersonagem(personagemManuel);

        mockMvc.perform(post("/personagem/salvarPersonagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transformarParaJson(personagemManuel)));

        MockHttpServletResponse resultado = mockMvc.perform(get("/personagem/getPersonagem/1"))
                .andReturn().getResponse();

                assertThat(HttpStatus.OK.value()).isEqualTo(resultado.getStatus());
                assertThat(personagemController.getPersonagem(personagemManuel.getId()).orElseThrow()).isEqualTo(personagemManuel);
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

        personagemManuela.setId(1);
        assertThat(personagemController.getPersonagem(1).orElseThrow()).isEqualTo(personagemManuela);
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
