package com.fate.GrimoireOfHeartApi.dto.response;

import com.fate.GrimoireOfHeartApi.model.atributo.AtributosDeBatalha;
import com.fate.GrimoireOfHeartApi.model.atributo.AtributosSociais;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonagemResponse{
    private String nomePersonagem;
    private AtributosDeBatalha atributosDeBatalha;
    private AtributosSociais atributosSociais;
}
