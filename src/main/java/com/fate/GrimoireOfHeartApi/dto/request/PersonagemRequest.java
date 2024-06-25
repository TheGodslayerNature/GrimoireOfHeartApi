package com.fate.GrimoireOfHeartApi.dto.request;

import com.fate.GrimoireOfHeartApi.model.atributo.AtributosDeBatalha;
import com.fate.GrimoireOfHeartApi.model.atributo.AtributosSociais;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonagemRequest{
        private String nomePersonagem;
        private AtributosDeBatalha atributosDeBatalha;
        private AtributosSociais atributosSociais;
}
