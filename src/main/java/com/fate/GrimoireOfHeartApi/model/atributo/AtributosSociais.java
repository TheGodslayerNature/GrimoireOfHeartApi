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
    public AtributosSociais() {

    }

//    public List<String> nomeDosAtributosSociais(){
//        return  meusAtributos.entrySet().stream().map( att -> String.format("%s: %o", att.getKey(), att.getValue())).toList();
//    }
//    public void adicionarPontos(String nomeDoAtributo, int ponto){
//        if (meusAtributos.containsKey(nomeDoAtributo)) meusAtributos.put(nomeDoAtributo, ponto);
//    }
}
