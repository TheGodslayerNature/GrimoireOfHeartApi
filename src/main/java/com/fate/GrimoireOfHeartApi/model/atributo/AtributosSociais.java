package com.fate.GrimoireOfHeartApi.model.atributo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Entity
@Data
public class AtributosSociais implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Long conhecimento = 0L;
    private Long disciplina = 0L;
    private Long empatia = 0L;
    private Long charme = 0L;
    private Long expressao = 0L;
    private Long coragem = 0L;
    public String printAtributosSociais() {
        return String.format("CONHECIMENTO: %d, DISCIPLINA: %d, EMPATIA: %d, CHARME: %d, EXPRESSÃO: %d, CORAGEM: %d", conhecimento,disciplina,empatia,charme,expressao,coragem);
    }
}
