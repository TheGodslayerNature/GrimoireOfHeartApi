package com.fate.GrimoireOfHeartApi.dto.response;

import com.fate.GrimoireOfHeartApi.model.Magia.Magia;
import com.fate.GrimoireOfHeartApi.model.persona.BonusPoints;
import com.fate.GrimoireOfHeartApi.model.tiposdemagia.TiposDeMagia;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaResponse {
    @NotBlank
    private String nome;
    private int nivel;
    private int pontosDeMagia;
    private String conviccao;
    private String habilidadeNatural;
    private ArrayList<TiposDeMagia> meusTiposDeMagia;
    private Magia[] deckDeMagia;
    private BonusPoints bonusPoints;
}
