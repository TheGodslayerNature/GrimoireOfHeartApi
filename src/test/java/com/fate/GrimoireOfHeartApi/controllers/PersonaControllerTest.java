package com.fate.GrimoireOfHeartApi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fate.GrimoireOfHeartApi.model.persona.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class PersonaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Persona persona;

    private String transformarParaJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    void criarUmaPersona() throws Exception {
        persona = new Persona("Peter pan");

        MockHttpServletResponse esperado = mockMvc.perform(post("/persona/salvarPersona")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transformarParaJson(persona))).andReturn().getResponse();

        assertThat(HttpStatus.OK.value()).isEqualTo(esperado.getStatus());
    }
}
