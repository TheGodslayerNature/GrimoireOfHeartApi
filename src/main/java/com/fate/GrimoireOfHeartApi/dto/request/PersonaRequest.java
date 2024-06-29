package com.fate.GrimoireOfHeartApi.dto.request;

import com.fate.GrimoireOfHeartApi.model.tiposdemagia.TiposDeMagia;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRequest {
    @NotBlank
    private String nome;
    private int nivel;
    private ArrayList<TiposDeMagia> meusTiposDeMagia;
}
