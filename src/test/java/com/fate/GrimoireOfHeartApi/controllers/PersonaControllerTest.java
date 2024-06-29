package com.fate.GrimoireOfHeartApi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fate.GrimoireOfHeartApi.model.persona.Persona;
import com.fate.GrimoireOfHeartApi.model.tiposdemagia.TiposDeMagia;
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


import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Persona persona;
    @Autowired
    private PersonaController personaController;

    private String transformarParaJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    void criarUmaPersona() throws Exception {
        persona = new Persona("Peter Pan");

        MockHttpServletResponse esperado = mockMvc.perform(post("/persona")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformarParaJson(persona))).andReturn().getResponse();

        assertThat(HttpStatus.CREATED.value()).isEqualTo(esperado.getStatus());
        assertThat(true).isEqualTo(esperado.getContentAsString().contains("Peter Pan"));
    }

    @Test
    void updateAnAlreadyExistingPersona() throws Exception {

        persona = new Persona("Peter pan");

        mockMvc.perform(post("/persona")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformarParaJson(persona))).andReturn().getResponse();

        ArrayList<TiposDeMagia> tipoDeMagias = new ArrayList<>();
        tipoDeMagias.add(TiposDeMagia.Fogo);
        Persona novaPersona = new Persona("Idun", tipoDeMagias);

        MockHttpServletResponse resultado = mockMvc.perform(put("/persona/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformarParaJson(novaPersona))).andReturn().getResponse();

        assertThat(HttpStatus.OK.value()).isEqualTo(resultado.getStatus());
        assertThat(true).isEqualTo(resultado.getContentAsString().contains("Idun"));
    }

    @Test
    void deveDeletarUmaPersonaJaExistente() throws Exception{
        persona = new Persona("Peter pan");

        mockMvc.perform(post("/persona")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformarParaJson(persona)));

        MockHttpServletResponse resultado = mockMvc.perform(delete("/persona/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformarParaJson(persona))).andReturn().getResponse();

        assertThat(HttpStatus.NO_CONTENT.value()).isEqualTo(resultado.getStatus());
        assertThat(true).isEqualTo(resultado.getContentAsString().isEmpty());
    }
}
